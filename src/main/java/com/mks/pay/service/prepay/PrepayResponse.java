package com.mks.pay.service.prepay;

import java.io.Serializable;

/**
 * 预支付响应结果 
 */
public class PrepayResponse implements Serializable {

    private static final long serialVersionUID = -7555394154160346763L;

    private boolean success;
    private String errCode;
    private String errMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
