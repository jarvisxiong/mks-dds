package com.mks.transport;

import com.mks.sap.Identifiable;

public interface Replier {

	void reply(Identifiable packet, boolean completeClose);

	/**
	 * send back packet,but do not close connection.<br>
	 * equals invoke sendback(packet, false);<br>
	 * 
	 * @param packet
	 */
	void reply(Identifiable packet);

	void closeSession(Identifiable packet, boolean immediately);
}
