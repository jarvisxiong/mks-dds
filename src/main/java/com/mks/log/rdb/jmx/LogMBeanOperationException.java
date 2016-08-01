package com.mks.log.rdb.jmx;

import com.mks.jmx.MBeanOperationException;

public class LogMBeanOperationException extends MBeanOperationException {

	private static final long serialVersionUID = -7886083550232201414L;

	public LogMBeanOperationException() {
		super();
	}

	public LogMBeanOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogMBeanOperationException(String message) {
		super(message);
	}

	public LogMBeanOperationException(Throwable cause) {
		super(cause);
	}
}
