package com.mks.dpl;

import java.util.Iterator;

import com.mks.dpl.plugin.PlugIn;
import com.mks.dpl.service.Service;


public interface PlugInServiceBus {

	void register(PlugIn plugIn);

	void unregister(String plugInKey);

	void activePlugIn(String plugInKey);

	void deactivePlugIn(String plugInKey);

	void activeService(String plugInKey, String serviceKey);

	void deactiveService(String plugInKey, String serviceKey);

	Iterator<PlugIn> getAllPlugIns();

	PlugIn lookupPlugIn(String plugInKey);

	Service lookupService(String plugInKey, String serviceKey);
}
