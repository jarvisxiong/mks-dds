package com.mks.dpl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.dpl.VarArgsFunctor;


public interface ServiceFunctor extends VarArgsFunctor {

	public static final Logger LOGGER = LoggerFactory.getLogger(ServiceFunctor.class);
	public static final ServiceFunctor DEFAULT = new ServiceFunctor() {

		@Override
		public Object execute(Object... arguments) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("ServiceFunctor.DEFAULT.execute(),return null.");
			return null;
		}
	};
}
