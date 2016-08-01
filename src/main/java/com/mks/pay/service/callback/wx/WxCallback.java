package com.mks.pay.service.callback.wx;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.pay.PayProvider;
import com.mks.pay.service.callback.Callback;
import com.mks.pay.service.callback.CallbackAnalysisException;
import com.mks.pay.service.callback.CallbackException;
import com.mks.utils.StringHelper;


/**
 * 微信回调处理 
 */
public class WxCallback implements Callback {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxCallback.class);
    
    public WxCallbackResponse callback(byte[] entity) {
        if (ArrayUtils.isEmpty(entity)) {
            throw new CallbackException("data has empty!");
        }
        WxCallbackArgument argument = WxCallbackArgument.of(StringHelper.convert(entity));
        if (null == argument) {
            
            throw new CallbackAnalysisException();
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Receive callback from Wx, Request parameters are [{}]", argument);
        }
        WxCallbackResponse response = new WxCallbackResponse();
        response.setArgument(argument);
        return response;
    }

    @Override
    public PayProvider getProvider() {
        return PayProvider.WX;
    }
}
