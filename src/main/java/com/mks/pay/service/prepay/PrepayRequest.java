package com.mks.pay.service.prepay;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;


/**
 * 预支付请求参数 
 */
public class PrepayRequest implements Serializable {

    private static final long serialVersionUID = -6576854146839194016L;

    /** APPID */
    private String appId;
    
    private String description;	/** 商品描述 */ 
    private String detail;		/** 商品详情 */
    
    /** 附加参数 */
    private String attach;
    
    private int fee;	/** 商品金额。单位：分 */
    
    private String ip;			/** 终端IP */
    
    private String notifyUrl;	/** 通知地址 */
    
    private String openId;		/** 用户标识 */
    
    public void validate() {
        Preconditions.checkArgument(StringUtils.isNotBlank(appId), "appId");
        Preconditions.checkArgument(StringUtils.isNotBlank(description), "description");
        Preconditions.checkArgument(fee > 0, "fee must > 0");
        Preconditions.checkArgument(StringUtils.isNotBlank(ip), "ip");
        Preconditions.checkArgument(StringUtils.isNotBlank(notifyUrl), "notifyUrl");
        Preconditions.checkArgument(StringUtils.isNotBlank(openId), "openId");
    }
    
    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDetail() {
        return detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    public String getAttach() {
        return attach;
    }
    
    public void setAttach(String attach) {
        this.attach = attach;
    }

    public int getFee() {
        return fee;
    }
    
    public void setFee(int fee) {
        this.fee = fee;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getNotifyUrl() {
        return notifyUrl;
    }
    
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
    
    public String getOpenId() {
        return openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
