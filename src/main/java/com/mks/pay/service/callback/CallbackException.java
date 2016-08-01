package com.mks.pay.service.callback;

import com.mks.pay.exceptions.PayException;


/**
 * Callback occur exception 
 */
public class CallbackException extends PayException {

    private static final long serialVersionUID = 4224481951217472178L;

    /**
     * 
     */
    public CallbackException() {
    }

    /**
     * @param message
     */
    public CallbackException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public CallbackException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public CallbackException(String message, Throwable cause) {
        super(message, cause);
    }

}
