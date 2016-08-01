package com.mks.dpl.event.listener;

import com.mks.dpl.event.AfterExecutionFiredEvent;
import com.mks.dpl.event.BeforeExecutionFiredEvent;
import com.mks.dpl.event.ExecutionFailedEvent;

public class RuntimeObjectServiceListener extends ServiceListenerAdapter {

	@Override
	public void beforeExecute(BeforeExecutionFiredEvent event) {
		RuntimeObjectHolder.setService(event.getService());
		RuntimeObjectHolder.setInvocation(event.getInvocation()[0]);
	}

	@Override
	public void afterExecute(AfterExecutionFiredEvent event) {
		RuntimeObjectHolder.removeAll();
	}

	@Override
	public void onExecuteFailed(ExecutionFailedEvent event) {
		RuntimeObjectHolder.removeAll();
	}
}
