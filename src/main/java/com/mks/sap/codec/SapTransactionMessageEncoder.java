package com.mks.sap.codec;

import org.apache.mina.core.buffer.IoBuffer;

import com.mks.sap.SapMessage;
import com.mks.sap.SapTransactionMessage;


class SapTransactionMessageEncoder extends SapMessageEncoder {

	@Override
	protected void encodeHeaderExt(SapMessage sapMessage, IoBuffer buffer)
			throws SapEncodingException {
		if (!(sapMessage instanceof SapTransactionMessage))
			throw new SapEncodingException("sapMessage expect SapTransactionMessage,but "
					+ sapMessage);
		SapTransactionMessage stm = (SapTransactionMessage) sapMessage;
		buffer.put(SapCodecUtils.encodeUUID(stm.getIdentification()));
	}
}
