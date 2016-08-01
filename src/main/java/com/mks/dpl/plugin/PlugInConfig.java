package com.mks.dpl.plugin;

import java.util.Iterator;

import com.mks.dpl.Config;
import com.mks.dpl.service.ServiceConfig;


public interface PlugInConfig extends Config {

	public Iterator<ServiceConfig> getServiceConfigs();
}
