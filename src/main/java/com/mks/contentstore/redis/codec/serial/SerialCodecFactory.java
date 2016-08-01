package com.mks.contentstore.redis.codec.serial;

import java.io.Serializable;

import com.mks.contentstore.redis.codec.BeanCodecFactory;
import com.mks.contentstore.redis.codec.BeanDecodeException;
import com.mks.contentstore.redis.codec.BeanDecoder;
import com.mks.contentstore.redis.codec.BeanEncodeException;
import com.mks.contentstore.redis.codec.BeanEncoder;
import com.mks.utils.SerializeUtils;


public final class SerialCodecFactory implements BeanCodecFactory {

	private final SerializeBeanEncoder encoder = new SerializeBeanEncoder();
	private final SerializeBeanDecoder decoder = new SerializeBeanDecoder();

	@Override
	public BeanDecoder getDecoder() {
		return decoder;
	}

	@Override
	public BeanEncoder getEncoder() {
		return encoder;
	}

	private static final class SerializeBeanEncoder implements BeanEncoder {

		@Override
		public byte[] encode(Object bean) throws BeanEncodeException {
			if (null == bean)
				throw new NullPointerException("bean");
			if (!(bean instanceof Serializable))
				throw new IllegalArgumentException("bean expect implement Serializable interface.");

			return SerializeUtils.encode((Serializable) bean);
		}
	}

	private static final class SerializeBeanDecoder implements BeanDecoder {

		@SuppressWarnings("unchecked")
		@Override
		public <T> T decode(byte[] bytes, Class<T> template) throws BeanDecodeException {
			if (null == bytes || 0 == bytes.length)
				throw new IllegalArgumentException("bytes is blank.");
			if (Serializable.class.isAssignableFrom(template))
				throw new IllegalArgumentException("template expect subclass of Serializable,but "
						+ template);

			return (T) SerializeUtils.decode(bytes, (Class) template);
		}
	}
}
