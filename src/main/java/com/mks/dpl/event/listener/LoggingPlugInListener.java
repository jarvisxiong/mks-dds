package com.mks.dpl.event.listener;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.dpl.event.ActivedEvent;
import com.mks.dpl.event.ConfigChangedEvent;
import com.mks.dpl.event.CreatedEvent;
import com.mks.dpl.event.DeActivedEvent;
import com.mks.dpl.event.DestroyedEvent;
import com.mks.dpl.event.StatusChangedEvent;
import com.mks.dpl.plugin.PlugIn;
import com.mks.dpl.service.Service;


//TODO:
public class LoggingPlugInListener extends PlugInListenerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingPlugInListener.class);
	private static final String PLUGIN_SUFFIX = "PLUGIN";

	@Override
	public void init() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void onActived(ActivedEvent event) {
		super.onActived(event);
		PlugIn plugIn = (PlugIn) event.getSource();
		String servDesc = getServiceDesc(plugIn);
		if (LOGGER.isInfoEnabled())
			LOGGER.info(PLUGIN_SUFFIX + "[" + plugIn.getConfig().getKey() + "-" + servDesc
					+ "] has actived.");
	}

	@Override
	public void onConfigChanged(ConfigChangedEvent event) {
		super.onConfigChanged(event);
		PlugIn plugIn = (PlugIn) event.getSource();
		String servDesc = getServiceDesc(plugIn);
		if (LOGGER.isInfoEnabled())
			LOGGER.info(PLUGIN_SUFFIX + "[" + plugIn.getConfig().getKey() + "-" + servDesc
					+ "] 's config has changed.");
	}

	@Override
	public void onCreated(CreatedEvent event) {
		super.onCreated(event);
		PlugIn plugIn = (PlugIn) event.getSource();
		String servDesc = getServiceDesc(plugIn);
		if (LOGGER.isInfoEnabled())
			LOGGER.info(PLUGIN_SUFFIX + "[" + plugIn.getConfig().getKey() + "-" + servDesc
					+ "] has created.");
	}

	private String getServiceDesc(PlugIn plugIn) {
		StringBuilder sbServDesc = new StringBuilder(64);
		for (Iterator<Service> itr = plugIn.getAllServices(); itr.hasNext();) {
			sbServDesc.append(itr.next().getKey()).append(",");
		}
		String servDesc = "";
		if (sbServDesc.length() > 0)
			servDesc = sbServDesc.substring(0, sbServDesc.length() - 1);
		return servDesc;
	}

	@Override
	public void onDeActived(DeActivedEvent event) {
		super.onDeActived(event);
		PlugIn plugIn = (PlugIn) event.getSource();
		String servDesc = getServiceDesc(plugIn);
		if (LOGGER.isInfoEnabled())
			LOGGER.info(PLUGIN_SUFFIX + "[" + plugIn.getConfig().getKey() + "-" + servDesc
					+ "] has deactived.");
	}

	@Override
	public void onDestroyed(DestroyedEvent event) {
		super.onDestroyed(event);
		PlugIn plugIn = (PlugIn) event.getSource();
		String servDesc = getServiceDesc(plugIn);
		if (LOGGER.isInfoEnabled())
			LOGGER.info(PLUGIN_SUFFIX + "[" + plugIn.getConfig().getKey() + "-" + servDesc
					+ "] has destroyed.");
	}

	@Override
	public void onStatusChanged(StatusChangedEvent event) {
		super.onStatusChanged(event);
		PlugIn plugIn = (PlugIn) event.getSource();
		String servDesc = getServiceDesc(plugIn);
		if (LOGGER.isInfoEnabled())
			LOGGER.info(PLUGIN_SUFFIX + "[" + plugIn.getConfig().getKey() + "-" + servDesc
					+ "] 's status has changed,current status ["
					+ (plugIn.getStatus().isRunning() ? "active" : "deactive") + "]");
	}
}
