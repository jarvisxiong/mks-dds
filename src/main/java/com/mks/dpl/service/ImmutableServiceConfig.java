package com.mks.dpl.service;

import com.mks.dpl.ImmutableConfig;
import com.mks.dpl.plugin.PlugInConfig;

public class ImmutableServiceConfig extends ImmutableConfig implements ServiceConfig {

	public ImmutableServiceConfig(String name, String key, String desc) {
		super(name, key, desc);
	}

	@Override
	public PlugInConfig getPlugInConfig() {
		// TODO Auto-generated method stub
		return null;
	}
}
