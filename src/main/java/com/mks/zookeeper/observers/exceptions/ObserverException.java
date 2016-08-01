package com.mks.zookeeper.observers.exceptions;
 
public class ObserverException extends RuntimeException {

    private static final long serialVersionUID = 8143078369565798219L;

    public ObserverException() {
    }

    /**
     * @param message
     */
    public ObserverException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ObserverException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ObserverException(String message, Throwable cause) {
        super(message, cause);
    }
}
