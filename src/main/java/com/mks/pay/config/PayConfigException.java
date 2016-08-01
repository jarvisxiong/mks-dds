package com.mks.pay.config;

import com.mks.pay.exceptions.PayException;


/**
 * 支付配置异常 
 */
public class PayConfigException extends PayException {

    private static final long serialVersionUID = -637431325281176808L;

    /**
     * 
     */
    public PayConfigException() {
    }

    /**
     * @param message
     */
    public PayConfigException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public PayConfigException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public PayConfigException(String message, Throwable cause) {
        super(message, cause);
    }

}
