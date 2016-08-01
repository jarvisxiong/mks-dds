package com.mks.dpl;

import java.util.Iterator;

import com.mks.dpl.event.listener.LifeCycleListener;
import com.mks.dpl.status.Status;


public interface LifeCycle<C extends Config, L extends LifeCycleListener> {

	void init();

	void destroy();

	C getConfig();

	Status getStatus();

	Iterator<L> getAllEventListener();
}
