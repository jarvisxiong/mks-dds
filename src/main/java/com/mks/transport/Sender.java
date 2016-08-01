package com.mks.transport;


public interface Sender {

	void init();

	void destroy();

	void send(Object message);
	
	SenderStatus getStatus();
}
