package com.mks.pay.config;

import java.util.Map;


/**
 * 支付配置服务接口 
 */
public interface PayConfigService {

    /**
     * 获取所有的支付配置。 
     */
    Map<String, PayConfig> getConfigs();
}
