package com.mks.transport.failover;
 
public class NotFoundAvailableSenderException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundAvailableSenderException() {
        super();
    }

    public NotFoundAvailableSenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundAvailableSenderException(String message) {
        super(message);
    }

    public NotFoundAvailableSenderException(Throwable cause) {
        super(cause);
    }
}