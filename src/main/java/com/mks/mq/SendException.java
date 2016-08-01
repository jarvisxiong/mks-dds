package com.mks.mq;
 
public class SendException extends RuntimeException {

    private static final long serialVersionUID = 6484824315128060157L;

    public SendException() {
        super();
    }

    public SendException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendException(String message) {
        super(message);
    }

    public SendException(Throwable cause) {
        super(cause);
    }
}
