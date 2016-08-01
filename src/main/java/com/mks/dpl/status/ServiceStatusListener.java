package com.mks.dpl.status;

import com.mks.dpl.event.AfterExecutionFiredEvent;
import com.mks.dpl.event.BeforeExecutionFiredEvent;
import com.mks.dpl.event.ExecutionFailedEvent;
import com.mks.dpl.event.listener.ServiceListener;

public class ServiceStatusListener extends StatusListener implements ServiceListener {

	public ServiceStatusListener(StatusHolder statusHolder) {
		super(statusHolder);
	}

	@Override
	public void afterExecute(AfterExecutionFiredEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeExecute(BeforeExecutionFiredEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onExecuteFailed(ExecutionFailedEvent event) {
		// TODO Auto-generated method stub
	}
}
