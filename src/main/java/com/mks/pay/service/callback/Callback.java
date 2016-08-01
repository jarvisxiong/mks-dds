package com.mks.pay.service.callback;

import com.mks.pay.PayProvider;


/**
 * 支付回调处理服务接口 
 */
public interface Callback {

    PayProvider getProvider();
}
