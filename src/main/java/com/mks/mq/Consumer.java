package com.mks.mq;


/**
 * 消息消费者 
 */
public interface Consumer {

    String DEFAULT_CONSUMER_GROUP = "consumer";
    
    /**
     * 暂停消费
     */
    void suspend();
    
    /**
     * 关闭连接
     */
    void shutdown();
}
