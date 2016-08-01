package com.mks.dpl.jmx;

import com.mks.dpl.service.ServiceStatistics;
import com.mks.jmx.model.GenericModelMBean;


public class ServiceStatisticsMBean extends GenericModelMBean<ServiceStatistics> {

	public ServiceStatisticsMBean(ServiceStatistics source) {
		super(source);
	}

	@Override
	protected boolean isOperation(String methodName, Class<?>[] paramTypes) {
		return false;
	}
}
