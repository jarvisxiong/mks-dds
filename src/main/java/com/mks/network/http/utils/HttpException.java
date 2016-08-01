package com.mks.network.http.utils;
 
public class HttpException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = 6970988386305134735L;

    public HttpException() {
        super();
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }
}
