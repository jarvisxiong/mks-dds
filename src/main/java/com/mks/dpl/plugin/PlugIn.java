package com.mks.dpl.plugin;

import java.util.Iterator;

import com.mks.dpl.ClassLoaderHolder;
import com.mks.dpl.LifeCycle;
import com.mks.dpl.event.listener.PlugInListener;
import com.mks.dpl.service.Service;
import com.mks.dpl.status.Status;
import com.mks.dpl.status.StatusSource;


public interface PlugIn extends LifeCycle<PlugInConfig, PlugInListener>, Status, StatusSource,
		PlugInConfig, ClassLoaderHolder {

	Service lookupService(String serviceKey);

	Iterator<Service> getAllServices();

	String getInstallPath();
}
