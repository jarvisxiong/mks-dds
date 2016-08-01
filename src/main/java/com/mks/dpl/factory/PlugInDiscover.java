package com.mks.dpl.factory;

import com.mks.dpl.hotswap.PlugInClassLoader;
import com.mks.dpl.plugin.PlugIn;

public interface PlugInDiscover {

	PlugIn discover(PlugInClassLoader classloader);// TODO:define discover
	// exception?
}
