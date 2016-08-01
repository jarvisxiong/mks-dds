package com.mks.session;

/**
 * Session already not exists or timeout. 
 */
public class SessionNotExistsException extends RuntimeException {

    private static final long serialVersionUID = 8804484388502420351L;

    private Trackable trackable;

    /**
     * 
     */
    public SessionNotExistsException() {
        super();
    }

    /**
     * @param trackable
     */
    public SessionNotExistsException(Trackable trackable) {
        super();
        this.trackable = trackable;
    }

    /**
     * @param message
     * @param cause
     */
    public SessionNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public SessionNotExistsException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public SessionNotExistsException(Throwable cause) {
        super(cause);
    }

    public Trackable getTrackable() {
        return trackable;
    }

    public void setTrackable(Trackable trackable) {
        this.trackable = trackable;
    }
}
