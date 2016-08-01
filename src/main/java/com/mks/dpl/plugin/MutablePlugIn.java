package com.mks.dpl.plugin;

import java.util.List;

import com.mks.dpl.MutableLifeCycle;
import com.mks.dpl.event.listener.PlugInListener;
import com.mks.dpl.service.Service;
import com.mks.dpl.status.MutableStatus;


public interface MutablePlugIn extends PlugIn, MutableLifeCycle<PlugInConfig, PlugInListener>,
		MutableStatus {

	void addService(Service service);

	void setServices(List<Service> services);

	void clearServices();

	void setInstallPath(String installPath);
}
