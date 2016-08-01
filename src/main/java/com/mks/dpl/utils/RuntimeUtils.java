package com.mks.dpl.utils;

import com.mks.dpl.event.listener.RuntimeObjectHolder;
import com.mks.dpl.service.Service;
import com.mks.dpl.service.ServiceStatistics;

public final class RuntimeUtils {

	private RuntimeUtils() {
	}

	public static Service getService() {
		Service service = RuntimeObjectHolder.getService();
		if (null == service)
			throw new IllegalStateException("Not in service execution cycle.");
		return service;
	}

	public static ServiceStatistics getServiceStatistics() {
		return getService().getStatistics();
	}

	public static Object getServiceAttachment() {
		return getService().getAttachment();
	}

	public static Object getInvocation() {
		Object iv = RuntimeObjectHolder.getInvocation();
		if (null == iv)
			throw new IllegalStateException("Not in service execution cycle.");
		return iv;
	}
}
