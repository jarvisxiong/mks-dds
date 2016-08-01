package com.mks.dpl.event.listener;

import com.mks.dpl.event.ActivedEvent;
import com.mks.dpl.event.ConfigChangedEvent;
import com.mks.dpl.event.CreatedEvent;
import com.mks.dpl.event.DeActivedEvent;
import com.mks.dpl.event.DestroyedEvent;
import com.mks.dpl.event.StatusChangedEvent;

public class LifeCycleListenerAdapter implements LifeCycleListener {

	@Override
	public void destroy() {
		// Empty Handler
	}

	@Override
	public void init() {
		// Empty Handler
	}

	@Override
	public void onActived(ActivedEvent event) {
		// Empty Handler
	}

	@Override
	public void onConfigChanged(ConfigChangedEvent event) {
		// Empty Handler
	}

	@Override
	public void onCreated(CreatedEvent event) {
		// Empty Handler
	}

	@Override
	public void onDeActived(DeActivedEvent event) {
		// Empty Handler
	}

	@Override
	public void onDestroyed(DestroyedEvent event) {
		// Empty Handler
	}

	@Override
	public void onStatusChanged(StatusChangedEvent event) {
		// Empty Handler
	}
}
