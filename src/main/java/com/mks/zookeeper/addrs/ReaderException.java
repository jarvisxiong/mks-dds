package com.mks.zookeeper.addrs;

public class ReaderException extends RuntimeException {

    private static final long serialVersionUID = 3874251926321320889L;

    public ReaderException() {
    }

    public ReaderException(String message) {
        super(message);
    }

    public ReaderException(Throwable cause) {
        super(cause);
    }

    public ReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
