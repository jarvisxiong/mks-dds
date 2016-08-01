package com.mks.dpl.status;

import com.mks.dpl.event.listener.PlugInListener;

public class PlugInStatusListener extends StatusListener implements PlugInListener {

	public PlugInStatusListener(StatusHolder statusHolder) {
		super(statusHolder);
	}
}
