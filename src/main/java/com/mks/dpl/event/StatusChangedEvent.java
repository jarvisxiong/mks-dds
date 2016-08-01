package com.mks.dpl.event;

import com.mks.dpl.status.Status;

public class StatusChangedEvent extends LifeCycleEvent {

	private static final long serialVersionUID = 1L;

	private final Status oldStatus;
	private final Status newStatus;

	public StatusChangedEvent(Object source, Status oldStatus, Status newStatus) {
		super(source);
		if (null == oldStatus)
			throw new NullPointerException("oldStatus");
		if (null == newStatus)
			throw new NullPointerException("newStatus");
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
	}

	public Status getOldStatus() {
		return oldStatus;
	}

	public Status getNewStatus() {
		return newStatus;
	}
}
