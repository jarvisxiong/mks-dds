package com.mks.mq.alions;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mks.mq.ConsumeStatus;
import com.mks.mq.Consumer;
import com.mks.mq.Message;
import com.mks.mq.MessageListener;
import com.mks.utils.BaseLifeCycleSupport;


/**
 * 基于阿里云ONS的消息实现<br>
 * 请参考：<a href="https://www.aliyun.com/product/ons">https://www.aliyun.com/product/ons</a> 
 */
public class AlionsConsumer extends BaseLifeCycleSupport implements Consumer {

    private final Properties properties = new Properties();
    private com.aliyun.openservices.ons.api.Consumer consumer;
    private final MessageListener messageListener;
    private String subExpression;
    
    public AlionsConsumer(String consumerId, String accessKey, String secretKey, 
            MessageListener messageListener) {
        super();
        Preconditions.checkNotNull(messageListener);
        properties.put(PropertyKeyConst.ConsumerId, consumerId);
        properties.put(PropertyKeyConst.AccessKey, accessKey);
        properties.put(PropertyKeyConst.SecretKey, secretKey);
        consumer = ONSFactory.createConsumer(properties);
        if (StringUtils.isBlank(subExpression))
            this.subExpression = "*";
        this.messageListener = messageListener;
    }
    
    @Override
    protected void doInit() {
        if (null != consumer) {
            String topic = messageListener.getTopic();
            if (StringUtils.isBlank(topic))
                throw new RuntimeException("topic must has not blank!");
            consumer.subscribe(topic, subExpression, new com.aliyun.openservices.ons.api.MessageListener() {
                
                @Override
                public Action consume(com.aliyun.openservices.ons.api.Message message, ConsumeContext context) {
                    Message msg = new Message();
                    msg.setTopic(message.getTopic());
                    msg.setBody(message.getBody());
                    msg.setTags(message.getTag());
                    msg.setMsgId(message.getMsgID());
                    ConsumeStatus status = messageListener.consume(Lists.newArrayList(msg), context);
                    switch (status) {
                        case CONSUME_SUCCESS:
                            return Action.CommitMessage;
                        default:
                            return Action.ReconsumeLater;
                    }
                }
            });
            if (consumer.isClosed())
                consumer.start();
        }
    }

    @Override
    protected void doDestroy() {
        shutdown();
    }

    @Override
    public void suspend() {
        if (consumer.isStarted())
            consumer.shutdown();
    }

    @Override
    public void shutdown() {
        if (consumer.isStarted())
            consumer.shutdown();
    }

    public void setSubExpression(String subExpression) {
        this.subExpression = subExpression;
    }
}
