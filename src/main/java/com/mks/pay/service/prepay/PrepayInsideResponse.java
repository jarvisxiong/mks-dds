package com.mks.pay.service.prepay;

import java.io.Serializable;

import com.mks.pay.ability.Fail;
import com.mks.pay.ability.Success;


/**
 * 支付响应信息 
 */
public abstract class PrepayInsideResponse implements Serializable, Success, Fail {

    private static final long serialVersionUID = -5820995114867896004L;

}
