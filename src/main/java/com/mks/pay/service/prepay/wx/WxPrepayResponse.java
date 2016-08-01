package com.mks.pay.service.prepay.wx;

import com.mks.pay.service.prepay.PrepayResponse;

/**
 * 微信支付响应结果 
 */
public class WxPrepayResponse extends PrepayResponse {

    private static final long serialVersionUID = -7780340412484918974L;

    private String prepayId;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
