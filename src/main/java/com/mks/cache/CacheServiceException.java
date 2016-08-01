package com.mks.cache;

import com.mks.utils.exception.SystemRuntimeException;

public class CacheServiceException extends SystemRuntimeException {

	private static final long serialVersionUID = 1L;

	public CacheServiceException() {
		super();
	}

	public CacheServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public CacheServiceException(String message) {
		super(message);
	}

	public CacheServiceException(Throwable cause) {
		super(cause);
	}

}
