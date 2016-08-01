package com.mks.dpl.event.listener;

import com.mks.dpl.event.ActivedEvent;
import com.mks.dpl.event.AfterExecutionFiredEvent;
import com.mks.dpl.event.BeforeExecutionFiredEvent;
import com.mks.dpl.event.ConfigChangedEvent;
import com.mks.dpl.event.CreatedEvent;
import com.mks.dpl.event.DeActivedEvent;
import com.mks.dpl.event.DestroyedEvent;
import com.mks.dpl.event.ExecutionFailedEvent;
import com.mks.dpl.event.StatusChangedEvent;

public interface ServiceListener extends LifeCycleListener {

	void beforeExecute(BeforeExecutionFiredEvent event);

	/**
	 * only service executed <b>successfully</b>,trigger this event.
	 * 
	 * @param event
	 */
	void afterExecute(AfterExecutionFiredEvent event);

	/**
	 * only service executed <b>failed</b>,trigger this event.
	 * 
	 * @param event
	 */
	void onExecuteFailed(ExecutionFailedEvent event);

	@Override
	void init();

	@Override
	void destroy();

	@Override
	void onCreated(CreatedEvent event);

	@Override
	void onDestroyed(DestroyedEvent event);

	@Override
	void onActived(ActivedEvent event);

	@Override
	void onDeActived(DeActivedEvent event);

	@Override
	void onStatusChanged(StatusChangedEvent event);

	@Override
	void onConfigChanged(ConfigChangedEvent event);
}
