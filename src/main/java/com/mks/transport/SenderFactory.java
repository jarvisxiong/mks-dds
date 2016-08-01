package com.mks.transport;

import java.net.InetSocketAddress;

public interface SenderFactory {

	Sender create(InetSocketAddress targetAddress);
}
