package com.mks.mq;

import java.io.Serializable;

/**
 * 发送结果 
 */
public class SendResult implements Serializable {

    private static final long serialVersionUID = -7827529082085323499L;

    private String msgId;

    public SendResult() {
        super();
    }

    public SendResult(String msgId) {
        super();
        this.msgId = msgId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
