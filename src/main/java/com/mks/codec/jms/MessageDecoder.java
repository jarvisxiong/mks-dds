package com.mks.codec.jms;

import com.mks.codec.jms.bean.TLVMessageBean;


public interface MessageDecoder {

	public TLVMessageBean decode(byte[] bytes);

}
