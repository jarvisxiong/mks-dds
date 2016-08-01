package com.mks.codec.tlv;

import com.mks.codec.Codec;
import com.mks.codec.error.IllegalTLVContentHandler;

public interface TLVDecoder<R extends Object> extends Codec<byte[], R, TLVDecoderProvider, Object> {

	/**
	 * @deprecated 
	 */
	boolean isObjectDecoder();

	void setIllegalTLVContentHandler(IllegalTLVContentHandler illegalTLVContentHandler);
}
