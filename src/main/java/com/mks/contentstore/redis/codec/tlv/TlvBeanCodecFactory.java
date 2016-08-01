package com.mks.contentstore.redis.codec.tlv;

import com.mks.contentstore.redis.codec.BeanCodecFactory;
import com.mks.contentstore.redis.codec.BeanDecoder;
import com.mks.contentstore.redis.codec.BeanEncoder;

public class TlvBeanCodecFactory implements BeanCodecFactory {

	private final TlvBeanEncoder tlvBeanEncoder;
	private final TlvBeanDecoder tlvBeanDecoder;

	public TlvBeanCodecFactory(TlvBeanEncoder tlvBeanEncoder, TlvBeanDecoder tlvBeanDecoder) {
		super();
		this.tlvBeanEncoder = tlvBeanEncoder;
		this.tlvBeanDecoder = tlvBeanDecoder;
	}

	@Override
	public BeanDecoder getDecoder() {
		return tlvBeanDecoder;
	}

	@Override
	public BeanEncoder getEncoder() {
		return tlvBeanEncoder;
	}
}
