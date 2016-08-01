package com.mks.dpl.status;

import com.mks.dpl.event.ActivedEvent;
import com.mks.dpl.event.CreatedEvent;
import com.mks.dpl.event.DeActivedEvent;
import com.mks.dpl.event.DestroyedEvent;
import com.mks.dpl.event.listener.LifeCycleListenerAdapter;

public abstract class StatusListener extends LifeCycleListenerAdapter {

	private final StatusHolder statusHolder;

	public StatusListener(StatusHolder statusHolder) {
		if (null == statusHolder)
			throw new NullPointerException("statusHolder");
		this.statusHolder = statusHolder;
	}

	@Override
	public void onCreated(CreatedEvent event) {
		super.onCreated(event);
		StatusSource statusSource = (StatusSource) event.getSource();
		try {
			statusHolder.syncStatusOf(statusSource);
		} catch (Exception e) {
			// TODO:StatusShiftException?StatusPersistentException?
			throw new RuntimeException("StatusListener.onCreated:", e);
		}
	}

	@Override
	public void onDestroyed(DestroyedEvent event) {
		super.onDestroyed(event);
		StatusSource statusSource = (StatusSource) event.getSource();
		try {
			statusHolder.removeStatusOf(statusSource);
		} catch (Exception e) {
			throw new RuntimeException("StatusListener.onDestroyed:", e);
		}
	}

	@Override
	public void onActived(ActivedEvent event) {
		super.onActived(event);
		StatusSource statusSource = (StatusSource) event.getSource();
		try {
			statusHolder.addOrUpdateStatusOf(statusSource);
		} catch (Exception e) {
			throw new RuntimeException("StatusListener.onActived:", e);
		}
	}

	@Override
	public void onDeActived(DeActivedEvent event) {
		super.onDeActived(event);
		StatusSource statusSource = (StatusSource) event.getSource();
		try {
			statusHolder.addOrUpdateStatusOf(statusSource);
		} catch (Exception e) {
			throw new RuntimeException("StatusListener.onDeActived:", e);
		}
	}
}
