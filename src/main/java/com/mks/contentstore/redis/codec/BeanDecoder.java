package com.mks.contentstore.redis.codec;

public interface BeanDecoder {

	<T> T decode(byte[] bytes, Class<T> template) throws BeanDecodeException;
}
