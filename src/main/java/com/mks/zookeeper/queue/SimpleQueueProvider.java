package com.mks.zookeeper.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.utils.BaseLifeCycleSupport;
import com.mks.utils.GsonHelper;


/**
 * 简单的消息队列生产者 
 */
public class SimpleQueueProvider extends BaseLifeCycleSupport implements QueueProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleQueueProvider.class);
    private final SimpleQueue simpleQueue;
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public SimpleQueueProvider(SimpleQueue simpleQueue) {
        this.simpleQueue = simpleQueue;
    }
    
    @Override
    protected void doInit() {
    }

    @Override
    protected void doDestroy() {
        pool.shutdown();
    }

    @Override
    public void asynchPut(final Object object) {
        pool.submit(new Runnable() {
            
            @Override
            public void run() {
                put(object);
            }
        });
    }

    public void put(final Object object) {
        byte[] data = GsonHelper.convert(object);
        if (null == data) {
            return;
        }
        try {
            while (!simpleQueue.offer(data)) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("Unable to add new element to the queue!");
                }
                simpleQueue.poll();
            }
        }
        catch (Exception e) {
            LOGGER.error("put: ", e);
        }
    }
}
