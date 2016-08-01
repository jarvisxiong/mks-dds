package com.mks.mybatis.exception;

public class MyBatisException extends Exception {

    private static final long serialVersionUID = -466980864716946654L;

    public MyBatisException() {
    }

    public MyBatisException(String message) {
        super(message);
    }

    public MyBatisException(Throwable cause) {
        super(cause);
    }

    public MyBatisException(String message, Throwable cause) {
        super(message, cause);
    }

}
