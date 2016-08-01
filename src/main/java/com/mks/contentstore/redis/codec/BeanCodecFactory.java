package com.mks.contentstore.redis.codec;

public interface BeanCodecFactory {

	BeanEncoder getEncoder();

	BeanDecoder getDecoder();
}
