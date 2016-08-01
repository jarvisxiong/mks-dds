package com.mks.dpl.event.listener;

import java.util.EventListener;

import com.mks.dpl.event.ActivedEvent;
import com.mks.dpl.event.ConfigChangedEvent;
import com.mks.dpl.event.CreatedEvent;
import com.mks.dpl.event.DeActivedEvent;
import com.mks.dpl.event.DestroyedEvent;
import com.mks.dpl.event.StatusChangedEvent;


public interface LifeCycleListener extends EventListener {

	void init();

	void destroy();

	void onCreated(CreatedEvent event);

	void onDestroyed(DestroyedEvent event);

	void onActived(ActivedEvent event);

	void onDeActived(DeActivedEvent event);

	void onStatusChanged(StatusChangedEvent event);

	void onConfigChanged(ConfigChangedEvent event);
}
