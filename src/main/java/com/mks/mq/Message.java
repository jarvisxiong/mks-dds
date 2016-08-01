package com.mks.mq;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * 消息 
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -6006678448904972962L;
    
    /**
     * 消息主题
     */
    private String topic;
    /** 过滤标签 */
    private String tags;
    /**
     * 消息标志，系统不做干预，完全由应用决定如何使用
     */
    private int flag;
    /**
     * 消息体
     */
    private byte[] body;
    
    private String msgId;
    
    /** messageDelayLevel=1s 5s 10s 30s 1m 3m 5m 10m 30m 50m 1h 3h 5h 12h */
    private int delayTimeLevel = 0;
    
    public Message() {
        super();
    }

    public Message(String topic, byte[] body) {
        super();
        this.topic = topic;
        this.body = body;
    }

    public Message(String topic, int flag, byte[] body) {
        super();
        this.topic = topic;
        this.flag = flag;
        this.body = body;
    }

    public Message(String topic, String tags, int flag, byte[] body) {
        super();
        this.topic = topic;
        this.tags = tags;
        this.flag = flag;
        this.body = body;
    }

    public String getTopic() {
        return topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public int getFlag() {
        return flag;
    }
    
    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    public byte[] getBody() {
        return body;
    }
    
    public void setBody(byte[] body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    
    public int getDelayTimeLevel() {
        return delayTimeLevel;
    }
    
    /**
     * 消息延时投递时间级别，0表示不延时，大于0表示特定延时级别（具体级别在服务器端定义）
     * @param delayTimeLevel
     * @see org.bigmouth.nvwa.mq.DelayTimeLevel
     */
    public void setDelayTimeLevel(int delayTimeLevel) {
        this.delayTimeLevel = delayTimeLevel;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
