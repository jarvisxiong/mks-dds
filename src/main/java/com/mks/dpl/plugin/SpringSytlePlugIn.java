package com.mks.dpl.plugin;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

import com.mks.dpl.event.listener.PlugInListener;
import com.mks.dpl.status.Status;


public class SpringSytlePlugIn extends GenericPlugIn implements ApplicationContextAware {

	private ApplicationContext context;

	public SpringSytlePlugIn() {
		super();
	}

	public SpringSytlePlugIn(PlugInConfig config, Status status) {
		super(config, status);
	}

	public SpringSytlePlugIn(PlugInConfig config, Status status, List<PlugInListener> listeners) {
		super(config, status, listeners);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	@Override
	public void destroy() {
		super.destroy();
		destroyApplicationContext();
	}

	private void destroyApplicationContext() {
		AbstractApplicationContext aac = (AbstractApplicationContext) context;
		aac.destroy();
	}
}
