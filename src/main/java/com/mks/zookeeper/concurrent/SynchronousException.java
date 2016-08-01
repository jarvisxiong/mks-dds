package com.mks.zookeeper.concurrent;


/**
 * 同步任务服务异常 
 */
public class SynchronousException extends RuntimeException {

    private static final long serialVersionUID = -1767665674562255986L;

    /**
     * 
     */
    public SynchronousException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public SynchronousException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public SynchronousException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public SynchronousException(Throwable cause) {
        super(cause);
    }
}
