package com.mks.log.rdb;

import com.mks.dpl.event.listener.PlugInListener;
import com.mks.dpl.event.listener.PlugInListenerAdapter;
import com.mks.dpl.plugin.MutablePlugIn;

public final class PlugInManageRecordControllerFactory implements RecordControllerFactory {

	private final MutablePlugIn plugIn;
	private final RecordControllerFactory impl;

	public PlugInManageRecordControllerFactory(MutablePlugIn plugIn, RecordControllerFactory impl) {
		super();
		if (null == plugIn)
			throw new NullPointerException("plugIn");
		if (null == impl)
			throw new NullPointerException("impl");
		this.plugIn = plugIn;
		this.impl = impl;
	}

	@Override
	public RecordController create(Object logInfo) {
		final RecordController rc = impl.create(logInfo);
		PlugInListener listener = new PlugInListenerAdapter() {

			@Override
			public void destroy() {
				rc.destroy();
			}
		};
		plugIn.addEventListener(listener);
		return rc;
	}
}
