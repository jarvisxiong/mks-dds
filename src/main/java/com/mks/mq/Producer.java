package com.mks.mq;
 

/**
 * 消息生产者 
 */
public interface Producer {

    String DEFAULT_PRODUCER_GROUP = "producer";
    
    /**
     * 发送消息
     * @param message 需要发送的消息
     * @return 如果发送失败，会直接抛异常
     */
    SendResult send(Message message) throws SendException;
}
