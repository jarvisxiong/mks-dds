package com.mks.pay.exceptions;


/**
 * 支付异常 
 */
public class PayException extends RuntimeException {

    private static final long serialVersionUID = 6100556956809665301L;

    /**
     * 
     */
    public PayException() {
    }

    /**
     * @param message
     */
    public PayException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public PayException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public PayException(String message, Throwable cause) {
        super(message, cause);
    }

}
