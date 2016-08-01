package com.mks.dpl.service;

import com.mks.dpl.MutableLifeCycle;
import com.mks.dpl.event.listener.ServiceListener;
import com.mks.dpl.status.MutableStatus;

public interface MutableService extends Service, MutableLifeCycle<ServiceConfig, ServiceListener>,
		MutableStatus {

	void setAttachment(Object attachment);
}
