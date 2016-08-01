package com.mks.zookeeper.observers.exceptions;


public class AlreadyExistException extends ObserverException {

    private static final long serialVersionUID = 3634621363427219847L;

    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(Throwable cause) {
        super(cause);
    }
}
