package com.mks.dpl.event.listener;

import com.mks.dpl.ClassLoaderHolder;

public class ReviseContextClassLoaderPlugInListener extends
		ReviseContextClassLoaderListener<PlugInListener> implements PlugInListener {

	public ReviseContextClassLoaderPlugInListener(ClassLoaderHolder classLoaderHolder,
			PlugInListener listener) {
		super(classLoaderHolder, listener);
	}
}
