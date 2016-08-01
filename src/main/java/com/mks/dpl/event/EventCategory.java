package com.mks.dpl.event;

public enum EventCategory {

	PLUGIN, SERVICE;

	public boolean isPlugInEvent() {
		return this == PLUGIN;
	}

	public boolean isServiceEvent() {
		return this == SERVICE;
	}
}
