package com.mks.transport;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoConnector;

public interface TargetSessionLocatorBuilder {

	TargetSessionLocator build(IoConnector connector, InetSocketAddress endPointAddress,
			String threadName);
}
