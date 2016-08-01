package com.mks.dpl.event;

import java.util.EventObject;

import com.mks.dpl.plugin.PlugIn;
import com.mks.dpl.service.Service;


public class LifeCycleEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final EventCategory eventCategory;
	private final long timestamp;

	public LifeCycleEvent(Object source) {
		super(source);
		if (source instanceof PlugIn) {
			eventCategory = EventCategory.PLUGIN;
		} else if (source instanceof Service) {
			eventCategory = EventCategory.SERVICE;
		} else {
			throw new IllegalArgumentException("source expect PlugIn or Service,but "
					+ source.getClass());
		}
		timestamp = System.currentTimeMillis();
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public final long getTimestamp() {
		return timestamp;
	}
}
