package com.mks.mq.rocketmq;

import org.apache.commons.lang.StringUtils;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.mks.mq.Message;
import com.mks.mq.Producer;
import com.mks.mq.SendException;
import com.mks.mq.SendResult;
import com.mks.utils.BaseLifeCycleSupport;


public class RocketMQProducer extends BaseLifeCycleSupport implements Producer {
    
    private final DefaultMQProducer producer;
    private final String nameSrvAddr;

    private String producerGroup;

    public RocketMQProducer(String nameSrvAddr) {
        if (StringUtils.isBlank(producerGroup))
            this.producerGroup = Producer.DEFAULT_PRODUCER_GROUP;
        this.producer = new DefaultMQProducer(producerGroup);
        this.nameSrvAddr = nameSrvAddr;
        this.producer.setNamesrvAddr(nameSrvAddr);
    }

    @Override
    public SendResult send(Message message) throws SendException {
        try {
            com.alibaba.rocketmq.common.message.Message msg = new com.alibaba.rocketmq.common.message.Message();
            msg.setTopic(message.getTopic());
            msg.setBody(message.getBody());
            msg.setTags(message.getTags());
            int level = message.getDelayTimeLevel();
            if (level > 0)
                msg.setDelayTimeLevel(level);
            com.alibaba.rocketmq.client.producer.SendResult sendResult = producer.send(msg);
            if (null == sendResult)
                throw new SendException();
            if (sendResult.getSendStatus() != SendStatus.SEND_OK)
                throw new SendException(String.format("Send failed! %s", sendResult.getSendStatus()));
            
            return new SendResult(sendResult.getMsgId());
        }
        catch (Exception e) {
            throw new SendException(e);
        }
    }

    @Override
    protected void doInit() {
        try {
            producer.start();
        }
        catch (MQClientException e) {
            throw new RuntimeException("doInit:", e);
        }
    }

    @Override
    protected void doDestroy() {
        producer.shutdown();
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

    public String getNameSrvAddr() {
        return nameSrvAddr;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }
}
