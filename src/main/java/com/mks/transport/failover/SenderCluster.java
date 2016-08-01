package com.mks.transport.failover;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.Atomics;
import com.mks.distributed.Endpoint;
import com.mks.distributed.EndpointKeyHost;
import com.mks.distributed.monitor.ChangeMonitor;
import com.mks.distributed.monitor.SubPathChangeEvent;
import com.mks.distributed.monitor.SubPathChangeEvent.SubEventType;
import com.mks.distributed.monitor.SubPathChangeListener;
import com.mks.transport.MinaReplier;
import com.mks.transport.Sender;
import com.mks.transport.SenderFactory;
import com.mks.transport.SenderStatus;
import com.mks.transport.failover.jmx.SenderClusterMBean;
import com.mks.utils.BaseLifeCycleSupport;
import com.mks.utils.Pair;
 
public class SenderCluster extends BaseLifeCycleSupport implements Sender, SubPathChangeListener, SenderClusterMBean {

    private static final Logger LOG = LoggerFactory.getLogger(MinaReplier.class);
    private static final String SENDER_ARRANGE_THREAD_NAME = "senderCluster-arrange-thread";

    private final String watchPath;
    private SenderFactory senderFactory;
    private final ChangeMonitor changeMonitor;
    private final AtomicReference<SenderLocator> senderLocator = Atomics.newReference();

    private final AtomicLong sendMessages = new AtomicLong(0L);

    private final AtomicReference<ListMultimap<EndpointKeyHost, Pair<Endpoint, Sender>>> senders = Atomics
            .newReference();

    private ExecutorService arrangeTaskExec;

    public SenderCluster(String watchPath, ChangeMonitor changeMonitor) {
        Preconditions.checkArgument(StringUtils.isNotBlank(watchPath), "watchPath is blank.");
        Preconditions.checkNotNull(changeMonitor, "changeMonitor");

        this.watchPath = watchPath;
        this.changeMonitor = changeMonitor;
        this.senderLocator.set(SenderLocator.create());
    }

    public void setSenderFactory(SenderFactory senderFactory) {
        this.senderFactory = senderFactory;
    }

    @Override
    protected void doInit() {
        Preconditions.checkNotNull(senderFactory, "senderFactory");

        ListMultimap<EndpointKeyHost, Pair<Endpoint, Sender>> m = ArrayListMultimap.create();
        senders.set(m);
        arrangeTaskExec = Executors.newSingleThreadExecutor(new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, SENDER_ARRANGE_THREAD_NAME);
            }
        });
        this.changeMonitor.addListener(watchPath, this);
    }

    @Override
    protected void doDestroy() {
        arrangeTaskExec.shutdownNow();
        ListMultimap<EndpointKeyHost, Pair<Endpoint, Sender>> mes = senders.get();

        for (Pair<Endpoint, Sender> s : mes.values()) {
            try {
                s.getSecond().destroy();
            }
            catch (Exception e) {
                LOG.error("doDestroy:", e);
            }
        }

        mes = ArrayListMultimap.create();
        senders.set(mes);
    }

    @Override
    public void send(Object message) {

        ListMultimap<EndpointKeyHost, Pair<Endpoint, Sender>> _senders = null;

        int retryCount = 0;
        for (;;) {
            _senders = senders.get();
            if (_senders.size() > 0) {
                break;
            }

            if (retryCount++ < 20) {
                if (LOG.isInfoEnabled())
                    LOG.info("can not found any senders,wait 1000ms then retry,retry count:" + retryCount);
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException("send:", e);
                }
                continue;
            }
            else {
                throw new NotFoundAvailableSenderException("has retry count 20 times for 20s.");
            }
        }

        Sender hitSender = null;
        try {
            hitSender = senderLocator.get().lookup(_senders);
            if (null == hitSender)
                throw new NotFoundAvailableSenderException("hit Sender is null.");
        }
        catch (NotFoundAvailableSenderException e) {
            throw e;
        }

        doSend(message, hitSender);
    }

    private void doSend(Object message, Sender hitSender) {
        sendMessages.incrementAndGet();
        hitSender.send(message);
    }

    private void updateSenders(Map<Endpoint, Pair<Endpoint, Sender>> newSnapshot) {
        ListMultimap<EndpointKeyHost, Pair<Endpoint, Sender>> target = ArrayListMultimap.create();
        for (Pair<Endpoint, Sender> p : newSnapshot.values()) {
            target.put(EndpointKeyHost.of(p.getFirst()), p);
        }

        senders.set(target);
        if (LOG.isInfoEnabled()) {
            LOG.info("Current senders:" + target);
        }
    }

    @Override
    public void onChanged(SubPathChangeEvent event) {
        final Set<Endpoint> changedSenders = getChangedSenders(event);
        SubEventType eventType = event.getType();
        Runnable arrangeTask = null;

        if (LOG.isDebugEnabled())
            LOG.debug("SenderCluster onChanged fired,event:" + event);

        if (SubEventType.CHILD_ADDED == eventType) {

            arrangeTask = new Runnable() {

                @Override
                public void run() {
                    // ensure invoke "getCurrentSenders" in single thread.
                    final Map<Endpoint, Pair<Endpoint, Sender>> currentSenders = getCurrentSenders();

                    for (Endpoint e : changedSenders) {
                        if (null == currentSenders.get(e)) {
                            Sender newSender = senderFactory.create(e.getAddress());
                            newSender.init();

                            currentSenders.put(e, Pair.of(e, newSender));
                        }
                    }

                    updateSenders(currentSenders);
                }
            };
        }
        else if (SubEventType.CHILD_REMOVED == eventType) {
            arrangeTask = new Runnable() {

                @Override
                public void run() {
                    // ensure invoke "getCurrentSenders" in single thread.
                    final Map<Endpoint, Pair<Endpoint, Sender>> currentSenders = getCurrentSenders();
                    for (Endpoint e : changedSenders) {
                        Pair<Endpoint, Sender> removeSender = currentSenders.remove(e);
                        if (null == removeSender) {
                            continue;
                        }
                        removeSender.getSecond().destroy();
                    }
                    updateSenders(currentSenders);
                }
            };
        }
        else if (SubEventType.CHILD_UPDATED == eventType) {
            arrangeTask = new Runnable() {

                @Override
                public void run() {
                    // ensure invoke "getCurrentSenders" in single thread.
                    final Map<Endpoint, Pair<Endpoint, Sender>> currentSenders = getCurrentSenders();
                    // copy currentSenders
                    final Map<Endpoint, Pair<Endpoint, Sender>> copyCurrentSenders = Maps.newHashMap();
                    for (Pair<Endpoint, Sender> v : currentSenders.values()) {
                        copyCurrentSenders.put(v.getFirst(), Pair.of(v.getFirst(), v.getSecond()));
                    }
                    for (Endpoint e : changedSenders) {
                        Pair<Endpoint, Sender> p = copyCurrentSenders.get(e);
                        if (null != p) {
                            copyCurrentSenders.put(e, Pair.of(e, p.getSecond()));
                        }
                    }
                    updateSenders(copyCurrentSenders);
                }
            };
        }
        else {
            // TODO:throw exception.
        }
        arrangeTaskExec.execute(arrangeTask);
    }

    private Map<Endpoint, Pair<Endpoint, Sender>> getCurrentSenders() {
        final Map<Endpoint, Pair<Endpoint, Sender>> currentMapSenders = Maps.newHashMap();
        for (Pair<Endpoint, Sender> p : senders.get().values()) {
            currentMapSenders.put(p.getFirst(), p);
        }
        return currentMapSenders;
    }

    private Set<Endpoint> getChangedSenders(SubPathChangeEvent event) {
        final Set<Endpoint> changedSenders = Sets.newHashSet();
        for (Pair<String, byte[]> p : event.getSubPaths()) {
            String[] factors = p.getFirst().split("_");
            Endpoint e = Endpoint.of(factors[0], Integer.parseInt(factors[1]), p.getSecond());
            changedSenders.add(e);
        }
        return changedSenders;
    }

    @Override
    public void setSenderLocator(String key) {
        this.senderLocator.set(SenderLocator.create(key));
    }

    @Override
    public String getSenders() {
        StringBuilder ret = new StringBuilder(64);
        for (Pair<Endpoint, Sender> p : this.senders.get().values()) {
            ret.append(p.getFirst()).append("|");
        }
        return 0 < ret.length() ? ret.substring(0, ret.length() - 1) : "";
    }

    @Override
    public String getSenderLocator() {
        return this.senderLocator.get().getKey();
    }

    @Override
    public long getSendMessages() {
        return sendMessages.get();
    }

    @Override
    public SenderStatus getStatus() {
        return SenderStatus.AVAILABLE;
    }
}
