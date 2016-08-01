package com.mks.dpl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.dpl.VarArgsClosure;


public interface ServiceClosure extends VarArgsClosure {

	public static final Logger LOGGER = LoggerFactory.getLogger(ServiceClosure.class);
	public static final ServiceClosure DEFAULT = new ServiceClosure() {

		@Override
		public void execute(Object... arguments) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("ServiceClosure.DEFAULT.execute(),ignore.");
			return;
		}
	};
}
