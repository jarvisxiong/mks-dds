package com.mks.zookeeper.listener.children;

import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.utils.BaseLifeCycleSupport;
import com.mks.zookeeper.ZkClientHolder;
import com.mks.zookeeper.listener.ChangeEventObject;
import com.mks.zookeeper.listener.ChangeEventObject.EventType;

 
public class ChildrenListenerImpl extends BaseLifeCycleSupport implements ChildrenMonitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChildrenListenerImpl.class);
    
    private PathChildrenCache pathChildrenCache;
    
    private final ZkClientHolder zkClientHolder;
    private final ChildrenChangeListener childrenChangeListener;
    private final String path;
    
    /**
     * @param zkClientHolder
     * @param childrenChangeListener
     */
    public ChildrenListenerImpl(ZkClientHolder zkClientHolder, ChildrenChangeListener childrenChangeListener, String path) {
        super();
        this.zkClientHolder = zkClientHolder;
        this.childrenChangeListener = childrenChangeListener;
        this.path = path;
    }

    @Override
    public void addListener(final ChildrenChangeListener listener) {
        if (null != listener) {
            final ChildrenListenerImpl monitor = this;
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    String path = event.getData().getPath();
                    byte[] data = event.getData().getData();
                    
                    EventType eventType = null;
                    Type type = event.getType();
                    if (type == Type.CHILD_ADDED)
                        eventType = EventType.CHILD_ADDED;
                    else if (type == Type.CHILD_UPDATED)
                        eventType = EventType.CHILD_UPDATED;
                    else if (type == Type.CHILD_REMOVED)
                        eventType = EventType.CHILD_REMOVED;
                    else
                        return;
                    listener.onChanged(monitor, ChangeEventObject.of(path, eventType, data));
                }
            });
        }
    }

    @Override
    protected void doInit() {
        pathChildrenCache = new PathChildrenCache(zkClientHolder.get(), path, true);
        if (null != childrenChangeListener)
            addListener(childrenChangeListener);
        try {
            pathChildrenCache.start();
        }
        catch (Exception e) {
            throw new RuntimeException("Children Listener start failured!");
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Monitor ({}) of ZooKeeper has been started!", path);
        }
    }

    @Override
    protected void doDestroy() {
        if (null != pathChildrenCache) {
            try {
                pathChildrenCache.close();
            }
            catch (IOException e) {
                LOGGER.error("destroy:", e);
            }
        }
    }

    @Override
    public CuratorFramework getCuratorClient() {
        return zkClientHolder.get();
    }
}
