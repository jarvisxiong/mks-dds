package com.mks.zookeeper.queue;


/**
 * 队列消费者 
 */
public interface SimpleQueueConsumer {

    /**
     * 消费队列的数据 
     */
    void consumeMessage(byte[] data);
}
