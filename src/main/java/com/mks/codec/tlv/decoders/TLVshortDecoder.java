package com.mks.codec.tlv.decoders;

import com.mks.codec.byteorder.ByteOrder;
import com.mks.codec.byteorder.NumberCodec;
import com.mks.codec.byteorder.NumberCodecFactory;
import com.mks.codec.tlv.TLVDecoderProvider;

public class TLVshortDecoder extends AbstractTLVDecoder<Short> {

	private static final String[] DECODER_KEYES = new String[] { short.class.getName(),
			Short.class.getName() };

	private TLVDecoderProvider decoderProvider;

	private NumberCodec numberCodec;

	private static final int DEFAULT_SHORT_LENGTH = 2;

	@Override
	public Short codec(byte[] source, Object additionInfo) {
		if (null == source)
			return null;

		return numberCodec.bytes2Short(source, DEFAULT_SHORT_LENGTH);
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
