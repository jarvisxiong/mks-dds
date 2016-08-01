package com.mks.contentstore.redis.codec.tlv;

import java.util.List;

import com.mks.codec.tlv.TLVEncoder;
import com.mks.codec.tlv.TLVEncoderProvider;
import com.mks.contentstore.redis.codec.BeanEncodeException;
import com.mks.contentstore.redis.codec.BeanEncoder;
import com.mks.utils.ByteUtils;


public final class TlvBeanEncoder implements BeanEncoder {

	private final TLVEncoderProvider tlvEncoderProvider;

	public TlvBeanEncoder(TLVEncoderProvider tlvEncoderProvider) {
		super();
		this.tlvEncoderProvider = tlvEncoderProvider;
	}

	@Override
	public byte[] encode(Object bean) throws BeanEncodeException {
		if (null == bean)
			throw new NullPointerException("bean");
		TLVEncoder<Object> tlvEncoder = tlvEncoderProvider.getObjectEncoder();
		List<byte[]> bytesList = tlvEncoder.codec(bean, null);
		return ByteUtils.union(bytesList);
	}
}
