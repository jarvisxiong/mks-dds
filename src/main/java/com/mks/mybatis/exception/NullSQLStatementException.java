package com.mks.mybatis.exception;

 
public class NullSQLStatementException extends MyBatisException {

    private static final long serialVersionUID = -3673466438388628061L;

    public NullSQLStatementException() {
        super();
    }

    public NullSQLStatementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullSQLStatementException(String message) {
        super(message);
    }

    public NullSQLStatementException(Throwable cause) {
        super(cause);
    }

}
