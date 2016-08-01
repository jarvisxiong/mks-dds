package com.mks.mq.alions;

import java.util.Properties;

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.mks.mq.DelayTimeLevel;
import com.mks.mq.Message;
import com.mks.mq.Producer;
import com.mks.mq.SendException;
import com.mks.mq.SendResult;
import com.mks.utils.BaseLifeCycleSupport;


/**
 * 基于阿里云ONS的消息实现<br>
 * 请参考：<a href="https://www.aliyun.com/product/ons">https://www.aliyun.com/product/ons</a> 
 */
public class AlionsProducer extends BaseLifeCycleSupport implements Producer {

    private final Properties properties = new Properties();
    private com.aliyun.openservices.ons.api.Producer producer;
    
    public AlionsProducer(String producerId, String accessKey, String secretKey) {
        super();
        properties.put(PropertyKeyConst.ProducerId, producerId);
        properties.put(PropertyKeyConst.AccessKey, accessKey);
        properties.put(PropertyKeyConst.SecretKey, secretKey);
        producer = ONSFactory.createProducer(properties);
    }

    @Override
    public SendResult send(Message message) {
        com.aliyun.openservices.ons.api.Message msg = new com.aliyun.openservices.ons.api.Message();
        msg.setTopic(message.getTopic());
        msg.setBody(message.getBody());
        msg.setTag(message.getTags());
        int level = message.getDelayTimeLevel();
        if (level > 0)
            msg.setStartDeliverTime(DelayTimeLevel.ofSystemTimeInMillis(level));
        com.aliyun.openservices.ons.api.SendResult sendResult = producer.send(msg);
        if (null == sendResult) 
            throw new SendException();
        return new SendResult(sendResult.getMessageId());
    }

    @Override
    protected void doInit() {
        if (null != producer && producer.isClosed())
            producer.start();
    }

    @Override
    protected void doDestroy() {
        if (null != producer && producer.isStarted()) {
            producer.shutdown();
        }
    }
}
