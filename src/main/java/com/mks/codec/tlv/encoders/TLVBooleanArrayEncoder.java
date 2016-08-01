package com.mks.codec.tlv.encoders;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.codec.tlv.TLVEncoder;
import com.mks.codec.tlv.TLVEncoderProvider;


/**
 * encode type of boolean[] or Boolean[]. 
 */
public class TLVBooleanArrayEncoder implements TLVEncoder<Object> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TLVBooleanArrayEncoder.class);

	private static final String[] ENCODER_KEYES = new String[] { Boolean[].class.getName(),
			boolean[].class.getName() };

	private TLVEncoderProvider encoderProvider;

	@Override
	public List<byte[]> codec(Object source, Object additionInfo) {
		if (null == encoderProvider)
			throw new IllegalStateException(
					"TLVBooleanArrayEncoder must be registered to encodeContext.");

		if (null == source)
			return new ArrayList<byte[]>();

		boolean[] _source = null;

		// primitive type priority
		if (source instanceof boolean[]) {
			_source = (boolean[]) source;
		} else if (source instanceof Boolean[]) {
			Boolean[] _s = (Boolean[]) source;
			_source = new boolean[_s.length];
			for (int i = 0; i < _s.length; i++)
				_source[i] = _s[i];
		} else {
			LOGGER.warn("source is not match type of boolean[] or Boolean[].");
			return new ArrayList<byte[]>();
		}

		byte[] result = new byte[_source.length];
		for (int i = 0; i < _source.length; i++) {
			result[i] = _source[i] ? (byte) 1 : (byte) 0;
		}

		List<byte[]> list = new ArrayList<byte[]>();
		list.add(result);

		return list;
	}

	@Override
	public String[] getCodecKeyes() {
		return ENCODER_KEYES;
	}

	@Override
	public TLVEncoderProvider getCodecProvider() {
		return encoderProvider;
	}

	@Override
	public void setCodecProvider(TLVEncoderProvider codecPrivoder) {
		this.encoderProvider = codecPrivoder;
	}

}
