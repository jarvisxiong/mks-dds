package com.mks.dpl.plugin;

import java.util.Iterator;

import com.mks.dpl.ImmutableConfig;
import com.mks.dpl.service.ServiceConfig;


public class ImmutablePlugInConfig extends ImmutableConfig implements PlugInConfig {

	public ImmutablePlugInConfig(String name, String key, String desc) {
		super(name, key, desc);
	}

	@Override
	public Iterator<ServiceConfig> getServiceConfigs() {
		// TODO Auto-generated method stub
		return null;
	}
}
