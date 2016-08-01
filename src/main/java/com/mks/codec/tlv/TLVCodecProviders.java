package com.mks.codec.tlv;

import com.mks.codec.tlv.decoders.TLVBooleanArrayDecoder;
import com.mks.codec.tlv.decoders.TLVBooleanDecoder;
import com.mks.codec.tlv.decoders.TLVByteArrayDecoder;
import com.mks.codec.tlv.decoders.TLVIntArrayDecoder;
import com.mks.codec.tlv.decoders.TLVIntDecoder;
import com.mks.codec.tlv.decoders.TLVIntegerArrayDecoder;
import com.mks.codec.tlv.decoders.TLVLongArrayDecoder;
import com.mks.codec.tlv.decoders.TLVObjectArrayDecoder;
import com.mks.codec.tlv.decoders.TLVObjectDecoder;
import com.mks.codec.tlv.decoders.TLVShortArrayDecoder;
import com.mks.codec.tlv.decoders.TLVStringArrayDecoder;
import com.mks.codec.tlv.decoders.TLVStringDecoder;
import com.mks.codec.tlv.decoders.TLVbArrayDecoder;
import com.mks.codec.tlv.decoders.TLVboolArrayDecoder;
import com.mks.codec.tlv.decoders.TLVbyteDecoder;
import com.mks.codec.tlv.decoders.TLVlArrayDecoder;
import com.mks.codec.tlv.decoders.TLVlongDecoder;
import com.mks.codec.tlv.decoders.TLVsArrayDecoder;
import com.mks.codec.tlv.decoders.TLVshortDecoder;
import com.mks.codec.tlv.encoders.TLVBooleanArrayEncoder;
import com.mks.codec.tlv.encoders.TLVBooleanEncoder;
import com.mks.codec.tlv.encoders.TLVByteArrayEncoder;
import com.mks.codec.tlv.encoders.TLVByteEncoder;
import com.mks.codec.tlv.encoders.TLVIntArrayEncoder;
import com.mks.codec.tlv.encoders.TLVIntEncoder;
import com.mks.codec.tlv.encoders.TLVLongArrayEncoder;
import com.mks.codec.tlv.encoders.TLVLongEncoder;
import com.mks.codec.tlv.encoders.TLVObjectArrayEncoder;
import com.mks.codec.tlv.encoders.TLVObjectEncoder;
import com.mks.codec.tlv.encoders.TLVShortArrayEncoder;
import com.mks.codec.tlv.encoders.TLVShortEncoder;
import com.mks.codec.tlv.encoders.TLVStringEncoder;

/**
 * Convenience factory methods. 
 * 
 */
public class TLVCodecProviders {

	public static TLVEncoderProvider newBigEndianTLVEncoderProvider() {
		TLVEncoderProvider encoderProvider = new TLVEncoderProviderImpl();

		encoderProvider.registerCodec(new TLVBooleanArrayEncoder());
		encoderProvider.registerCodec(new TLVBooleanEncoder());

		encoderProvider.registerCodec(new TLVByteArrayEncoder());
		encoderProvider.registerCodec(new TLVByteEncoder());

		encoderProvider.registerCodec(new TLVIntArrayEncoder());
		encoderProvider.registerCodec(new TLVIntEncoder());

		encoderProvider.registerCodec(new TLVLongArrayEncoder());
		encoderProvider.registerCodec(new TLVLongEncoder());

		encoderProvider.registerCodec(new TLVObjectArrayEncoder());
		encoderProvider.registerCodec(new TLVObjectEncoder());

		encoderProvider.registerCodec(new TLVShortArrayEncoder());
		encoderProvider.registerCodec(new TLVShortEncoder());

		encoderProvider.registerCodec(new TLVStringEncoder());

		return encoderProvider;
	}

	/**
	 * for list
	 * 
	 * @param tlvConfig
	 * @return
	 */
	public static TLVEncoderProvider newBigEndianTLVListEncoderProvider() {
		TLVEncoderProviderImpl encoderProvider = new TLVEncoderProviderImpl();

		encoderProvider.registerCodec(new TLVBooleanArrayEncoder());
		encoderProvider.registerCodec(new TLVBooleanEncoder());

		encoderProvider.registerCodec(new TLVByteArrayEncoder());
		encoderProvider.registerCodec(new TLVByteEncoder());

		encoderProvider.registerCodec(new TLVIntArrayEncoder());
		encoderProvider.registerCodec(new TLVIntEncoder());

		encoderProvider.registerCodec(new TLVLongArrayEncoder());
		encoderProvider.registerCodec(new TLVLongEncoder());

		encoderProvider.registerCodec(new TLVObjectArrayEncoder());

		encoderProvider.registerCodec(new TLVObjectEncoder());

		encoderProvider.registerCodec(new TLVShortArrayEncoder());
		encoderProvider.registerCodec(new TLVShortEncoder());

		encoderProvider.registerCodec(new TLVStringEncoder());

		TLVConfig tlvConfig = new TLVConfig();
		tlvConfig.setTagLen(1);
		tlvConfig.setLengthLen(2);

		encoderProvider.setTlvConfig(tlvConfig);

		return encoderProvider;
	}

	public static TLVDecoderProvider newBigEndianTLVDecoderProvider() {
		TLVDecoderProvider decoderProvider = new TLVDecoderProviderImpl();

		decoderProvider.registerCodec(new TLVBooleanDecoder());
		decoderProvider.registerCodec(new TLVboolArrayDecoder());
		decoderProvider.registerCodec(new TLVBooleanArrayDecoder());

		decoderProvider.registerCodec(new TLVbyteDecoder());
		decoderProvider.registerCodec(new TLVbArrayDecoder());
		decoderProvider.registerCodec(new TLVByteArrayDecoder());

		decoderProvider.registerCodec(new TLVIntDecoder());
		decoderProvider.registerCodec(new TLVIntArrayDecoder());
		decoderProvider.registerCodec(new TLVIntegerArrayDecoder());

		decoderProvider.registerCodec(new TLVlongDecoder());
		decoderProvider.registerCodec(new TLVlArrayDecoder());
		decoderProvider.registerCodec(new TLVLongArrayDecoder());

		decoderProvider.registerCodec(new TLVObjectArrayDecoder());
		decoderProvider.registerCodec(new TLVObjectDecoder());

		decoderProvider.registerCodec(new TLVsArrayDecoder());
		decoderProvider.registerCodec(new TLVShortArrayDecoder());
		decoderProvider.registerCodec(new TLVshortDecoder());

		decoderProvider.registerCodec(new TLVStringDecoder());
		decoderProvider.registerCodec(new TLVStringArrayDecoder());

		return decoderProvider;
	}

	public static TLVDecoderProvider newBigEndianUTF16BETLVDecoderProvider() {
		TLVDecoderProviderImpl decoderProvider = new TLVDecoderProviderImpl();

		decoderProvider.registerCodec(new TLVBooleanDecoder());
		decoderProvider.registerCodec(new TLVboolArrayDecoder());
		decoderProvider.registerCodec(new TLVBooleanArrayDecoder());

		decoderProvider.registerCodec(new TLVbyteDecoder());
		decoderProvider.registerCodec(new TLVbArrayDecoder());
		decoderProvider.registerCodec(new TLVByteArrayDecoder());

		decoderProvider.registerCodec(new TLVIntDecoder());
		decoderProvider.registerCodec(new TLVIntArrayDecoder());
		decoderProvider.registerCodec(new TLVIntegerArrayDecoder());

		decoderProvider.registerCodec(new TLVlongDecoder());
		decoderProvider.registerCodec(new TLVlArrayDecoder());
		decoderProvider.registerCodec(new TLVLongArrayDecoder());

		decoderProvider.registerCodec(new TLVObjectArrayDecoder());
		decoderProvider.registerCodec(new TLVObjectDecoder());

		decoderProvider.registerCodec(new TLVsArrayDecoder());
		decoderProvider.registerCodec(new TLVShortArrayDecoder());
		decoderProvider.registerCodec(new TLVshortDecoder());

		decoderProvider.registerCodec(new TLVStringDecoder());
		decoderProvider.registerCodec(new TLVStringArrayDecoder());

		decoderProvider.setCharset("UTF-16BE");

		return decoderProvider;
	}

	public static TLVDecoderProvider newBigEndianTLVDecoderProvider(TLVConfig config) {
		if (null == config)
			throw new NullPointerException("config");

		TLVDecoderProviderImpl decoderProvider = new TLVDecoderProviderImpl();

		decoderProvider.registerCodec(new TLVBooleanDecoder());
		decoderProvider.registerCodec(new TLVboolArrayDecoder());
		decoderProvider.registerCodec(new TLVBooleanArrayDecoder());

		decoderProvider.registerCodec(new TLVbyteDecoder());
		decoderProvider.registerCodec(new TLVbArrayDecoder());
		decoderProvider.registerCodec(new TLVByteArrayDecoder());

		decoderProvider.registerCodec(new TLVIntDecoder());
		decoderProvider.registerCodec(new TLVIntArrayDecoder());
		decoderProvider.registerCodec(new TLVIntegerArrayDecoder());

		decoderProvider.registerCodec(new TLVlongDecoder());
		decoderProvider.registerCodec(new TLVlArrayDecoder());
		decoderProvider.registerCodec(new TLVLongArrayDecoder());

		decoderProvider.registerCodec(new TLVObjectArrayDecoder());
		decoderProvider.registerCodec(new TLVObjectDecoder());

		decoderProvider.registerCodec(new TLVsArrayDecoder());
		decoderProvider.registerCodec(new TLVShortArrayDecoder());
		decoderProvider.registerCodec(new TLVshortDecoder());

		decoderProvider.registerCodec(new TLVStringDecoder());
		decoderProvider.registerCodec(new TLVStringArrayDecoder());

		decoderProvider.setTlvConfig(config);

		return decoderProvider;
	}

	public static TLVDecoderProvider newTightenBigEndianTLVDecoderProvider() {
		TLVDecoderProviderImpl decoderProvider = new TLVDecoderProviderImpl();

		decoderProvider.registerCodec(new TLVBooleanDecoder());
		decoderProvider.registerCodec(new TLVboolArrayDecoder());
		decoderProvider.registerCodec(new TLVBooleanArrayDecoder());

		decoderProvider.registerCodec(new TLVbyteDecoder());
		decoderProvider.registerCodec(new TLVbArrayDecoder());
		decoderProvider.registerCodec(new TLVByteArrayDecoder());

		decoderProvider.registerCodec(new TLVIntDecoder());
		decoderProvider.registerCodec(new TLVIntArrayDecoder());
		decoderProvider.registerCodec(new TLVIntegerArrayDecoder());

		decoderProvider.registerCodec(new TLVlongDecoder());
		decoderProvider.registerCodec(new TLVlArrayDecoder());
		decoderProvider.registerCodec(new TLVLongArrayDecoder());

		decoderProvider.registerCodec(new TLVObjectArrayDecoder());
		decoderProvider.registerCodec(new TLVObjectDecoder());

		decoderProvider.registerCodec(new TLVsArrayDecoder());
		decoderProvider.registerCodec(new TLVShortArrayDecoder());
		decoderProvider.registerCodec(new TLVshortDecoder());

		decoderProvider.registerCodec(new TLVStringDecoder());
		decoderProvider.registerCodec(new TLVStringArrayDecoder());

		TLVConfig config = new TLVConfig();
		config.setTagLen(1);
		config.setLengthLen(1);

		decoderProvider.setTlvConfig(config);

		return decoderProvider;
	}

}
