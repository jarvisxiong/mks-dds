package com.mks.dpl.event.listener;

import com.mks.dpl.ClassLoaderHolder;
import com.mks.dpl.event.AfterExecutionFiredEvent;
import com.mks.dpl.event.BeforeExecutionFiredEvent;
import com.mks.dpl.event.ExecutionFailedEvent;

public class ReviseContextClassLoaderServiceListener extends
		ReviseContextClassLoaderListener<ServiceListener> implements ServiceListener {

	public ReviseContextClassLoaderServiceListener(ClassLoaderHolder classLoaderHolder,
			ServiceListener listener) {
		super(classLoaderHolder, listener);
	}

	@Override
	public void beforeExecute(BeforeExecutionFiredEvent event) {
		getListener().beforeExecute(event);
	}

	@Override
	public void afterExecute(AfterExecutionFiredEvent event) {
		getListener().afterExecute(event);
	}

	@Override
	public void onExecuteFailed(ExecutionFailedEvent event) {
		getListener().onExecuteFailed(event);
	}
}
