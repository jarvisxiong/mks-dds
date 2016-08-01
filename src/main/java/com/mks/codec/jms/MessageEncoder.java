package com.mks.codec.jms;

import com.mks.codec.jms.bean.TLVMessageBean;


public interface MessageEncoder {

	public byte[] encode(TLVMessageBean bean);

}
