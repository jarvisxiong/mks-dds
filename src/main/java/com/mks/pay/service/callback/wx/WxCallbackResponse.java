package com.mks.pay.service.callback.wx;

import java.io.Serializable;

import com.mks.pay.PayDefaults;
import com.mks.pay.ability.ToXMLWrapper;
import com.mks.pay.service.callback.CallbackResponse;
import com.mks.utils.StringHelper;
import com.mks.utils.xml.Dom4jEncoder;


/**
 * 微信支付回调处理响应结果 
 */
public class WxCallbackResponse extends CallbackResponse {

    private static final long serialVersionUID = 6624958656268634906L;

    private WxCallbackArgument argument;
    private byte[] responseEntity;
    
    public WxCallbackArgument getArgument() {
        return argument;
    }
    
    public void setArgument(WxCallbackArgument argument) {
        this.argument = argument;
        this.responseEntity = getRespond();
    }
    
    public byte[] getResponseEntity() {
        return responseEntity;
    }
    
    private byte[] getRespond() {
        
        return (argument.isSuccess()) ? StringHelper.convert(new Respond().success().toXML()) 
                : StringHelper.convert(new Respond().fail().toXML());
    }
    
    class Respond implements Serializable, ToXMLWrapper {
        
        private static final long serialVersionUID = 1326520072891927633L;
        
        private String returnCode;
        private String returnMsg;
        
        public Respond() {
        }

        public Respond(String returnCode) {
            this.returnCode = returnCode;
        }

        public Respond(String returnCode, String returnMsg) {
            this.returnCode = returnCode;
            this.returnMsg = returnMsg;
        }

        public Respond success() {
            return new Respond(PayDefaults.SUCCESS);
        }
        
        public Respond fail() {
            return new Respond(PayDefaults.SUCCESS);
        }
        
        public String getReturnCode() {
            return returnCode;
        }
        
        public void setReturnCode(String returnCode) {
            this.returnCode = returnCode;
        }
        
        public String getReturnMsg() {
            return returnMsg;
        }
        
        public void setReturnMsg(String returnMsg) {
            this.returnMsg = returnMsg;
        }

        @Override
        public String toXML() {
            return Dom4jEncoder.encode(this, "/xml");
        }
    }
}
