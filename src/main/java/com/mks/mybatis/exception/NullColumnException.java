package com.mks.mybatis.exception;

 
public class NullColumnException extends MyBatisException {

    private static final long serialVersionUID = -6549866777471796753L;

    public NullColumnException() {
        super();
    }

    public NullColumnException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullColumnException(String message) {
        super(message);
    }

    public NullColumnException(Throwable cause) {
        super(cause);
    }

}
