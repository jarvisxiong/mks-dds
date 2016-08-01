package com.mks.sap.codec;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.statemachine.DecodingState;
import org.apache.mina.filter.codec.statemachine.DecodingStateMachine;

import com.google.common.collect.Maps;
import com.mks.sap.DefaultSapRequest;
import com.mks.sap.DefaultSapResponse;
import com.mks.sap.DefaultSapTransactionMessage;
import com.mks.sap.MessageType;
import com.mks.sap.MutableSapMessage;
import com.mks.sap.MutableSapRequest;
import com.mks.sap.MutableSapResponse;
import com.mks.sap.namecode.NameCodeMapper;

abstract class SapDecodingStateMachine extends DecodingStateMachine {

	private final NameCodeMapper ncMapper;
	private final IoSession session;

	private MutableSapMessage sapMessage;

	private static interface HeaderExtDecodingStateFactory {
		DecodingState create();
	}

	private final Map<MessageType, HeaderExtDecodingStateFactory> headerExtDecodingStateFactorys = Maps
			.newHashMap();

	public SapDecodingStateMachine(NameCodeMapper ncMapper, IoSession session) {
		if (null == ncMapper)
			throw new NullPointerException("ncMapper");
		if (null == session)
			throw new NullPointerException("session");
		this.ncMapper = ncMapper;
		this.session = session;

		registerHeaderExtDSF(MessageType.MESSAGE, new SapHeaderExtBaseDSF());
		registerHeaderExtDSF(MessageType.TRANSACTION_MESSAGE, new SapHeaderExtTransactionDSF());
		registerHeaderExtDSF(MessageType.REQUEST, new SapHeaderExtRequestDSF());
		registerHeaderExtDSF(MessageType.RESPONSE, new SapHeaderExtResponseDSF());
	}

	protected void registerHeaderExtDSF(MessageType messageType,
			HeaderExtDecodingStateFactory factory) {
		headerExtDecodingStateFactorys.put(messageType, factory);
	}

	@Override
	protected DecodingState init() throws Exception {
		sapMessage = null;
		return READ_SAP_HEADER_COMMON;
	}

	@Override
	protected void destroy() throws Exception {
		sapMessage = null;
	}

	private class SapHeaderExtBaseDSF implements HeaderExtDecodingStateFactory {

		@Override
		public DecodingState create() {
			return READ_SAP_HEADER_EXT_BASE;
		}
	}

	private class SapHeaderExtTransactionDSF implements HeaderExtDecodingStateFactory {

		@Override
		public DecodingState create() {
			return READ_SAP_HEADER_EXT_TRANSACTION;
		}
	}

	private class SapHeaderExtRequestDSF implements HeaderExtDecodingStateFactory {

		@Override
		public DecodingState create() {
			sapMessage = new DefaultSapRequest(sapMessage);
			return new SapHeaderExtRequestDecodingState(ncMapper, session,
					(MutableSapRequest) sapMessage) {

				@Override
				protected DecodingState finishDecode(List<Object> childProducts,
						ProtocolDecoderOutput out) throws Exception {
					return READ_SAP_MESSAGE_CONTENT;
				}
			};
		}
	}

	private class SapHeaderExtResponseDSF implements HeaderExtDecodingStateFactory {

		@Override
		public DecodingState create() {
			sapMessage = new DefaultSapResponse(sapMessage);
			return new SapHeaderExtResponseDecodingState((MutableSapResponse) sapMessage) {

				@Override
				protected DecodingState finishDecode(List<Object> childProducts,
						ProtocolDecoderOutput out) throws Exception {
					return READ_SAP_MESSAGE_CONTENT;
				}
			};
		}
	}

	private final DecodingState READ_SAP_HEADER_COMMON = new SapHeaderCommonDecodingState() {

		@Override
		protected DecodingState finishDecode(List<Object> childProducts, ProtocolDecoderOutput out)
				throws Exception {
			if (null == childProducts || 0 == childProducts.size())
				throw new SapDecoderException("READ_SAP_HEADER_COMMON:childProducts is null.");
			sapMessage = (MutableSapMessage) childProducts.get(0);
			MessageType messageType = sapMessage.getMessageType();

			HeaderExtDecodingStateFactory decodingStateFactory = headerExtDecodingStateFactorys
					.get(messageType);
			if (null == decodingStateFactory)
				throw new SapDecoderException(
						"Can not found any HeaderExtDecodingStateFactory for messageType:"
								+ messageType);
			return decodingStateFactory.create();
		}
	};

	private final DecodingState READ_SAP_HEADER_EXT_BASE = new DecodingState() {

		@Override
		public DecodingState decode(IoBuffer buffer, ProtocolDecoderOutput out) throws Exception {
			return READ_SAP_MESSAGE_CONTENT;
		}

		@Override
		public DecodingState finishDecode(ProtocolDecoderOutput out) throws Exception {
			return READ_SAP_MESSAGE_CONTENT;
		}
	};

	private final DecodingState READ_SAP_HEADER_EXT_TRANSACTION = new SapHeaderExtTransactionDecodingState() {

		@Override
		protected DecodingState finishDecode(UUID tid, ProtocolDecoderOutput out) {
			sapMessage = new DefaultSapTransactionMessage(sapMessage, tid);
			return READ_SAP_MESSAGE_CONTENT;
		}
	};

	private final DecodingState READ_SAP_MESSAGE_CONTENT = new SapContentDecodingState() {

		@Override
		protected DecodingState finishDecode(List<Object> childProducts, ProtocolDecoderOutput out)
				throws Exception {
			if (null == childProducts || 0 == childProducts.size())
				throw new SapDecoderException("READ_SAP_MESSAGE_CONTENT:childProducts is null.");
			IoBuffer content = (IoBuffer) childProducts.get(0);
			sapMessage.setContent(content);
			out.write(sapMessage);
			return null;
		}
	};
}
