package com.mks.dpl.event.listener;

import com.mks.dpl.event.ActivedEvent;
import com.mks.dpl.event.ConfigChangedEvent;
import com.mks.dpl.event.CreatedEvent;
import com.mks.dpl.event.DeActivedEvent;
import com.mks.dpl.event.DestroyedEvent;
import com.mks.dpl.event.StatusChangedEvent;

public interface PlugInListener extends LifeCycleListener {

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
