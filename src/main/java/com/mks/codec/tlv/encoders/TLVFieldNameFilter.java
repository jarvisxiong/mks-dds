package com.mks.codec.tlv.encoders;

public interface TLVFieldNameFilter {

	boolean accept(String fieldName);

}
