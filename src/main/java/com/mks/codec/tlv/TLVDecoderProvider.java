package com.mks.codec.tlv;

import com.mks.codec.CodecProvider;
import com.mks.codec.byteorder.ByteOrder;

public interface TLVDecoderProvider extends CodecProvider<TLVDecoder<?>> {

	public TLVConfig getTlvConfig();

	public ByteOrder getByteOrder();

	public void setByteOrder(ByteOrder byteOrder);

	public int getIntByteLen();

	public void setIntByteLen(int byteLen);

	public TLVDecoder<Object> getObjectDecoder();
	
	public String getCharset();

}
