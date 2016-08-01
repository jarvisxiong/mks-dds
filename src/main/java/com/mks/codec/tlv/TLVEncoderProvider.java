package com.mks.codec.tlv;

import com.mks.codec.CodecProvider;
import com.mks.codec.byteorder.ByteOrder;

public interface TLVEncoderProvider extends CodecProvider<TLVEncoder<?>> {

	public TLVConfig getTlvConfig();

	public int getIntByteLen();

	public void setIntByteLen(int byteLen);

	public ByteOrder getByteOrder();

	public void setByteOrder(ByteOrder byteOrder);

	public TLVEncoder<Object> getObjectEncoder();

}
