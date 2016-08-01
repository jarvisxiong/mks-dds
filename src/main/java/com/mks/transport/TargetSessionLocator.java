package com.mks.transport;

import org.apache.mina.core.session.IoSession;

public interface TargetSessionLocator {

	IoSession lookup();
	
	int getSessions();

	void destroy();
}
