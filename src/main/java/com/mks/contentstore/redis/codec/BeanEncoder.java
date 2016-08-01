package com.mks.contentstore.redis.codec;

public interface BeanEncoder {

	byte[] encode(Object bean) throws BeanEncodeException;
}
