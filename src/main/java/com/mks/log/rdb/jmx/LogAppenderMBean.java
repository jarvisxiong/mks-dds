package com.mks.log.rdb.jmx;

import com.mks.jmx.model.GenericModelMBean;
import com.mks.log.rdb.LogAppender;


public final class LogAppenderMBean extends GenericModelMBean<LogAppender> {

	public LogAppenderMBean(LogAppender source) {
		super(source);
	}

	@Override
	protected boolean isAttribute(String attrName, Class<?> attrType) {
		if (attrName.matches("(consumeInMillis|pendingMessages)"))
			return true;
		return false;
	}

	@Override
	protected boolean isOperation(String methodName, Class<?>[] paramTypes) {
		return false;
	}
}
