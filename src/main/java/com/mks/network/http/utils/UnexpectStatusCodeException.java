package com.mks.network.http.utils;
 
public class UnexpectStatusCodeException extends HttpException {

    private static final long serialVersionUID = -2858964298957479893L;
    private final int statusCode;

    public UnexpectStatusCodeException(int statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public UnexpectStatusCodeException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public UnexpectStatusCodeException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
