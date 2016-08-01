package com.mks.zookeeper.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.utils.BaseLifeCycleSupport;

/**
 * 简单的消息队列消费者看守者 
 */
public class SimpleQueueConsumerKeeper extends BaseLifeCycleSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleQueueConsumerKeeper.class);
    private final SimpleQueue simpleQueue;

    private final ExecutorService pool = Executors.newSingleThreadExecutor();
    private SimpleQueueConsumer simpleQueueConsumer;

    public SimpleQueueConsumerKeeper(SimpleQueue simpleQueue) {
        super();
        this.simpleQueue = simpleQueue;
    }

    @Override
    protected void doInit() {
        pool.submit(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        byte[] take = simpleQueue.take();
                        if (ArrayUtils.isNotEmpty(take)) {
                            if (null != simpleQueueConsumer) {
                                simpleQueueConsumer.consumeMessage(take);
                            }
                        }
                    }
                    catch (Exception e) {
                        LOGGER.error("take:", e);
                    }
                }
            }
        });
    }

    @Override
    protected void doDestroy() {
        pool.shutdownNow();
    }

    public void setSimpleQueueConsumer(SimpleQueueConsumer simpleQueueConsumer) {
        this.simpleQueueConsumer = simpleQueueConsumer;
    }
}
