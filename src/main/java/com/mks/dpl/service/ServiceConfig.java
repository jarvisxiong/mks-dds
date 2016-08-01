package com.mks.dpl.service;

import com.mks.dpl.Config;
import com.mks.dpl.plugin.PlugInConfig;

public interface ServiceConfig extends Config {

	public PlugInConfig getPlugInConfig();
}
