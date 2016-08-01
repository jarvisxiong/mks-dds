package com.mks.pay.service.callback.alipay;

import com.mks.pay.service.callback.CallbackResponse;
import com.mks.utils.StringHelper;

/**
 * 支付宝回调处理结果 
 */
public class AlipayCallbackResponse extends CallbackResponse {

    private static final long serialVersionUID = 3807446253085764491L;

    private AlipayCallbackArgument argument;

    private byte[] responseEntity;

    public AlipayCallbackArgument getArgument() {
        return argument;
    }

    public void setArgument(AlipayCallbackArgument argument) {
        this.argument = argument;
        this.responseEntity = getRespond(argument);
    }

    public byte[] getResponseEntity() {
        return responseEntity;
    }

    private byte[] getRespond(AlipayCallbackArgument args) {
        return (args.isSuccess()) ? StringHelper.convert("success") : StringHelper.convert("fail");
    }
}
