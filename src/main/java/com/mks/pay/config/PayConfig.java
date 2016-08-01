package com.mks.pay.config;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;


/**
 * 支付配置信息 
 */
public class PayConfig {

    
    private String appId;	/** APPID */
    
    private String mchId;	/** 商户号 */
    
    private String appSecret;	/** APP_SECRET */ 
    private String apiKey;		/** APIKEY */
    
    private File pkcs12;		/** PKCS12证书文件 */
    
    private String urlPrepay;	/** 预支付地址 */
    
    public void validate() {
        Preconditions.checkArgument(StringUtils.isNotBlank(appId), "appId");
        Preconditions.checkArgument(StringUtils.isNotBlank(mchId), "mchId");
        Preconditions.checkArgument(StringUtils.isNotBlank(appSecret), "appSecret");
        Preconditions.checkArgument(null != pkcs12, "pkcs12");
        Preconditions.checkArgument(StringUtils.isNotBlank(urlPrepay), "urlPrepay");
    }
    
    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getMchId() {
        return mchId;
    }
    
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    
    public String getAppSecret() {
        return appSecret;
    }
    
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public File getPkcs12() {
        return pkcs12;
    }
    
    public void setPkcs12(File pkcs12) {
        this.pkcs12 = pkcs12;
    }

    public String getUrlPrepay() {
        return urlPrepay;
    }

    public void setUrlPrepay(String urlPrepay) {
        this.urlPrepay = urlPrepay;
    }
}
