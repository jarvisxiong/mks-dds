package com.mks.dpl.service;

import com.mks.dpl.ClassLoaderHolder;
import com.mks.dpl.LifeCycle;
import com.mks.dpl.event.listener.ServiceListener;
import com.mks.dpl.status.Status;
import com.mks.dpl.status.StatusSource;

public interface Service extends LifeCycle<ServiceConfig, ServiceListener>, Status, StatusSource,
		ServiceConfig, ClassLoaderHolder {

	ServiceStatistics getStatistics();

	/**
	 * e.g. extended info,extended statistics info.
	 * 
	 * @return
	 */
	Object getAttachment();
}
