package com.mks.codec.tlv;

import java.util.List;

import com.mks.codec.Codec;


public interface TLVEncoder<T extends Object> extends
		Codec<T, List<byte[]>, TLVEncoderProvider, Object> {

}
