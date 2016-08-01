package com.mks.mq.rocketmq;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mks.mq.ConsumeStatus;
import com.mks.mq.Consumer;
import com.mks.mq.Message;
import com.mks.mq.MessageListener;
import com.mks.utils.BaseLifeCycleSupport;


public class RocketMQConsumer extends BaseLifeCycleSupport implements Consumer {

    private final DefaultMQPushConsumer consumer;

    private final String namesrvAddr;
    private final MessageListener messageListener;
    private final String consumerGroup;
    private String subExpression;

    public RocketMQConsumer(String namesrvAddr, MessageListener messageListener) {
        this(namesrvAddr, messageListener, Consumer.DEFAULT_CONSUMER_GROUP);
    }

    public RocketMQConsumer(String namesrvAddr, MessageListener messageListener, String consumerGroup) {
        super();
        Preconditions.checkNotNull(messageListener);
        this.namesrvAddr = namesrvAddr;
        this.messageListener = messageListener;
        this.consumerGroup = consumerGroup;
        this.consumer = new DefaultMQPushConsumer(consumerGroup);
        this.consumer.setNamesrvAddr(namesrvAddr);
        if (StringUtils.isBlank(subExpression))
            this.subExpression = "*";
    }

    @Override
    protected void doInit() {
        try {
            Preconditions.checkArgument(StringUtils.isNotBlank(messageListener.getTopic()), "topic must has not blank!");
            consumer.subscribe(messageListener.getTopic(), subExpression);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    List<Message> messages = Lists.newArrayList();
                    for (MessageExt messageExt : msgs) {
                        Message msg = new Message();
                        msg.setMsgId(messageExt.getMsgId());
                        msg.setBody(messageExt.getBody());
                        msg.setTags(messageExt.getTags());
                        msg.setTopic(messageExt.getTopic());
                        msg.setFlag(messageExt.getFlag());
                        messages.add(msg);
                    }
                    ConsumeStatus status = messageListener.consume(messages, context);
                    switch (status) {
                        case CONSUME_SUCCESS:
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

                        default:
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
            });
            consumer.start();
        }
        catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDestroy() {
        shutdown();
    }

    public void suspend(){
        if (null != consumer) 
            consumer.suspend();
    }

    @Override
    public void shutdown() {
        if (null != consumer) 
            consumer.shutdown();
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public String getSubExpression() {
        return subExpression;
    }

    public void setSubExpression(String subExpression) {
        this.subExpression = subExpression;
    }

    public DefaultMQPushConsumer getConsumer() {
        return consumer;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }
}
