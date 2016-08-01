package com.mks.transport.jmx;

import com.mks.jmx.model.GenericModelMBean;
import com.mks.transport.MinaSender;


public class MinaSenderQueueMBean extends GenericModelMBean<MinaSender> {

	public MinaSenderQueueMBean(MinaSender source) {
		super(source);
	}

	@Override
	protected boolean isAttribute(String attrName, Class<?> attrType) {
		if ("pendingSendMessages".equals(attrName))
			return true;
		return false;
	}

	@Override
	protected boolean isOperation(String methodName, Class<?>[] paramTypes) {
		return false;
	}
}
