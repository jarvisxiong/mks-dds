package com.mks.codec.jms.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mks.codec.tlv.annotation.TLVArrayAttribute;
import com.mks.codec.tlv.annotation.TLVAttribute;


public class TLVArgumentsBean {

	@TLVAttribute(tag = 3, arrayAttributes = { @TLVArrayAttribute(tag = 4) })
	private Object[] arguments;
	
	public TLVArgumentsBean(Object[] args){
		arguments = args;
	}

	public Object[] getArguments() {
		return arguments;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
