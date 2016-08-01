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
import com.mks.dpl.event.StatusChangedEvent;
import com.mks.dpl.service.Service;


public class LoggingServiceListener extends ServiceListenerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingServiceListener.class);
	private static final String SERVICE_SUFFIX = "SERVICE";

	@Override
	public void beforeExecute(BeforeExecutionFiredEvent event) {
		super.beforeExecute(event);
		Service service = (Service) event.getSource();
		if (LOGGER.isInfoEnabled())
			LOGGER.info(SERVICE_SUFFIX + "[" + service.getConfig().getKey()
					+ "] is ready to execute.");
	}

	@Override
	public void afterExecute(AfterExecutionFiredEvent event) {
		super.afterExecute(event);
		Service service = (Service) event.getService();
		if (LOGGER.isInfoEnabled())
			LOGGER.info(SERVICE_SUFFIX + "[" + service.getConfig().getKey() + "] has executed.");
	}

	@Override
	public void onActived(ActivedEvent event) {
		super.onActived(event);
		Service service = (Service) event.getSource();
		if (LOGGER.isInfoEnabled())
			LOGGER.info(SERVICE_SUFFIX + "[" + service.getConfig().getKey() + "] has actived.");
	}

	@Override
	public void onConfigChanged(ConfigChangedEvent event) {
		super.onConfigChanged(event);
		Service service = (Service) event.getSource();
		if (LOGGER.isInfoEnabled())
			LOGGER.info(SERVICE_SUFFIX + "[" + service.getConfig().getKey()
					+ "] 's config has changed.");
	}

	@Override
	public void onCreated(CreatedEvent event) {
		super.onCreated(event);
		Service service = (Service) event.getSource();
		if (LOGGER.isInfoEnabled())
			LOGGER.info(SERVICE_SUFFIX + "[" + service.getConfig().getKey() + "] has created.");
	}

	@Override
	public void onDeActived(DeActivedEvent event) {
		super.onDeActived(event);
		Service service = (Service) event.getSource();
		if (LOGGER.isInfoEnabled())
			LOGGER.info(SERVICE_SUFFIX + "[" + service.getConfig().getKey() + "] has deactived.");
	}

	@Override
	public void onDestroyed(DestroyedEvent event) {
		super.onDestroyed(event);
		Service service = (Service) event.getSource();
		if (LOGGER.isInfoEnabled())
			LOGGER.info(SERVICE_SUFFIX + "[" + service.getConfig().getKey() + "] has destroyed.");
	}

	@Override
	public void onStatusChanged(StatusChangedEvent event) {
		super.onStatusChanged(event);
		Service service = (Service) event.getSource();
		if (LOGGER.isInfoEnabled())
			LOGGER.info(SERVICE_SUFFIX + "[" + service.getConfig().getKey()
					+ "] 's status has changed,current status ["
					+ (service.getStatus().isRunning() ? "active" : "deactive") + "]");
	}
}
