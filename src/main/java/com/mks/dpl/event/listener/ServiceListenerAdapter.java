package com.mks.dpl.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.dpl.event.ActivedEvent;
import com.mks.dpl.event.AfterExecutionFiredEvent;
import com.mks.dpl.event.BeforeExecutionFiredEvent;
import com.mks.dpl.event.ConfigChangedEvent;
import com.mks.dpl.event.CreatedEvent;
import com.mks.dpl.event.DeActivedEvent;
import com.mks.dpl.event.DestroyedEvent;
import com.mks.dpl.event.ExecutionFailedEvent;
import com.mks.dpl.event.StatusChangedEvent;


public class ServiceListenerAdapter implements ServiceListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceListenerAdapter.class);

	@Override
	public void init() {
		// Empty Handler
	}

	@Override
	public void destroy() {
		// Empty Handler
	}

	@Override
	public void afterExecute(AfterExecutionFiredEvent event) {
		// Empty Handler
	}

	@Override
	public void beforeExecute(BeforeExecutionFiredEvent event) {
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

	@Override
	public void onExecuteFailed(ExecutionFailedEvent event) {
		if (LOGGER.isWarnEnabled()) {
			LOGGER.warn("[Service]" + event.getService().getName()
					+ " EXCEPTION, please implement " + getClass().getName()
					+ ".exceptionCaught() for proper handling:", event.getCause());
		}
	}
}
