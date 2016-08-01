package com.mks.pay.service.callback.alipay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.pay.PayProvider;
import com.mks.pay.service.callback.Callback;
import com.mks.pay.service.callback.CallbackAnalysisException;
import com.mks.pay.service.callback.CallbackException;
import com.mks.utils.StringHelper;


/**
 * 支付宝回调处理 
 */
public class AlipayCallback implements Callback {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlipayCallback.class);
    
    public AlipayCallbackResponse callback(String uri) {
        if (StringHelper.isBlank(uri)) {
            throw new CallbackException("uri has be blank!");
        }
        AlipayCallbackArgument args = AlipayCallbackArgument.of(uri);
        if (null == args) {
            
            throw new CallbackAnalysisException();
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Receive callback from Alipay, Request parameters are [{}]", args);
        }
        AlipayCallbackResponse response = new AlipayCallbackResponse();
        response.setArgument(args);
        return response;
    }
    
    @Override
    public PayProvider getProvider() {
        return PayProvider.ALIPAY;
    }
}
