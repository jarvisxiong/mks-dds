package com.mks.pay.service.prepay.wx;

import com.mks.pay.config.PayConfig;
import com.mks.pay.config.PayConfigService;
import com.mks.pay.service.prepay.AbstractPrepay;
import com.mks.pay.service.prepay.PrepayInsideRequest;
import com.mks.pay.service.prepay.PrepayRequest;
import com.mks.pay.service.prepay.PrepayResponse;


public class WxPrepayServiceImpl extends AbstractPrepay {

    public WxPrepayServiceImpl(PayConfigService configService) {
        super(configService);
    }

    @Override
    protected PrepayInsideRequest getPayRequest(PrepayRequest argument, PayConfig config) {
        return WxPrepayInsideRequest.of(argument, config);
    }

    @Override
    protected PrepayResponse convert(byte[] array) {
        WxPrepayInsideResponse inside = WxPrepayInsideResponse.of(array);
        if (null == inside) {
            return null;
        }
        WxPrepayResponse response = new WxPrepayResponse();
        response.setSuccess(inside.isSuccess());
        response.setErrCode(inside.getStatusCode());
        response.setErrMsg(inside.getStatusMessage());
        response.setPrepayId(inside.getPrepayId());
        return response;
    }
}
