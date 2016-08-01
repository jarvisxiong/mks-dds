package com.mks.codec.compress;

import java.util.ArrayList;
import java.util.List;

import com.mks.codec.tlv.TLVEncoder;
import com.mks.codec.tlv.TLVEncoderProvider;
import com.mks.utils.ByteUtils;
import com.mks.utils.Transformer;


/**
 * Separate data. 
 */
public class SeparateFileAdapter implements Transformer<byte[], byte[]> {

	private TLVEncoderProvider tlvListEncoderProvider;

	private CompressSegmentFactory listSegmentFactory;

	@Override
	public byte[] transform(byte[] from) {
		if (null == from || from.length == 0)
			throw new IllegalArgumentException("from");
		return encode(from);
	}

	private byte[] encode(byte[] data) {
		List<byte[]> compressDataList = new ArrayList<byte[]>();
		TLVEncoder<Object> objectTLVEncoder = tlvListEncoderProvider.getObjectEncoder();
		List<CompressSegment> listSegments = listSegmentFactory.createListSegments(data);
		for (CompressSegment c : listSegments) {
			List<byte[]> bytes = objectTLVEncoder.codec(c, null);
			compressDataList.addAll(bytes);
		}
		byte[] compressData = ByteUtils.union(compressDataList);
		return compressData;
	}

	public void setTlvListEncoderProvider(TLVEncoderProvider tlvListEncoderProvider) {
		this.tlvListEncoderProvider = tlvListEncoderProvider;
	}

	public void setListSegmentFactory(CompressSegmentFactory listSegmentFactory) {
		this.listSegmentFactory = listSegmentFactory;
	}
}
