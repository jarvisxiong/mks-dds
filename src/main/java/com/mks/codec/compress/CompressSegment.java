package com.mks.codec.compress;

import java.io.Serializable;

import com.mks.codec.tlv.annotation.TLVAttribute;
import com.mks.utils.GZipUtils;


/**
 * Using gzip to compress data.  
 */
public class CompressSegment implements Serializable {

	private static final long serialVersionUID = 2700827788350904270L;

	@TLVAttribute(tag = 99)
	private byte[] content = new byte[0];

	public CompressSegment(byte[] content) {
		if (null == content || content.length == 0)
			return;
		this.content = GZipUtils.compress(content);
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
