package com.mks.zookeeper.observers.exceptions;

 
public class CharacterException extends ObserverException {

    private static final long serialVersionUID = 2460101812712988770L;

    /**
     * 
     */
    public CharacterException() {
    }

    /**
     * @param message
     */
    public CharacterException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public CharacterException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public CharacterException(String message, Throwable cause) {
        super(message, cause);
    }

}
