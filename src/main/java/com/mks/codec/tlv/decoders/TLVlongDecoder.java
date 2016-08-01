package com.mks.codec.tlv.decoders;

import com.mks.codec.byteorder.ByteOrder;
import com.mks.codec.byteorder.NumberCodec;
import com.mks.codec.byteorder.NumberCodecFactory;
import com.mks.codec.tlv.TLVDecoderProvider;

/**
 * 8 bytes = 1 long. 
 * 
 */
public class TLVlongDecoder extends AbstractTLVDecoder<Long> {

	private static final String[] DECODER_KEYES = new String[] { long.class.getName(),
			Long.class.getName() };

	private TLVDecoderProvider decoderProvider;

	private NumberCodec numberCodec;

	private static final int DEFAULT_LONG_LENGTH = 8;

	@Override
	public Long codec(byte[] source, Object additionInfo) {
		if (null == decoderProvider || null == numberCodec)
			throw new IllegalStateException("TLVlongDecoder must be registered to decodeContext.");

		if (null == source)
			return 0L;

		// not ignore meta data.

		return numberCodec.bytes2Long(source, DEFAULT_LONG_LENGTH);
	}

	@Override
	public String[] getCodecKeyes() {
		return DECODER_KEYES;
	}

	@Override
	public TLVDecoderProvider getCodecProvider() {
		return decoderProvider;
	}

	@Override
	public void setCodecProvider(TLVDecoderProvider codecPrivoder) {
		this.decoderProvider = codecPrivoder;
		ByteOrder byteOrder = decoderProvider.getByteOrder();
		this.numberCodec = NumberCodecFactory.fetchNumberCodec(byteOrder);
	}

	@Override
	public boolean isObjectDecoder() {
		return false;
	}

}
