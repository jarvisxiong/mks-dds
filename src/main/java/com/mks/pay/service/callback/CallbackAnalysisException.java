package com.mks.pay.service.callback;


/**
 * Callback analysis exception 
 */
public class CallbackAnalysisException extends CallbackException {

    /**  */
    private static final long serialVersionUID = -286237662431828900L;

    /**
     * 
     */
    public CallbackAnalysisException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public CallbackAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public CallbackAnalysisException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public CallbackAnalysisException(Throwable cause) {
        super(cause);
    }
}
