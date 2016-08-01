package com.mks.dpl;

import java.util.List;

import com.mks.dpl.event.listener.LifeCycleListener;
import com.mks.dpl.status.Status;


public interface MutableLifeCycle<C extends Config, L extends LifeCycleListener> extends
		LifeCycle<C, L> {

	void setConfig(C config);

	void setStatus(Status status);

	void addEventListener(L listener);

	void addEventListeners(List<L> listeners);

	void clearEventListeners();
}
