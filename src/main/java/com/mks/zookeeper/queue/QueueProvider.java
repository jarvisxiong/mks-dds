package com.mks.zookeeper.queue;


/**
 * 消息队列生产者 
 */
public interface QueueProvider {

    /**
     * 生产一个消息
     * 
     * @param object
     */
    void put(final Object object);
    
    /**
     * 异步生产一个消息
     * 
     * @param object
     */
    void asynchPut(final Object object);
}
