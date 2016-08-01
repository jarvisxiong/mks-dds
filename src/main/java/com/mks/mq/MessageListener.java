package com.mks.mq;

import java.util.List;


/**
 * 消息消费者监听器 
 */
public interface MessageListener {

    String getTopic();
    
    ConsumeStatus consume(List<Message> messages, Object context);
}
