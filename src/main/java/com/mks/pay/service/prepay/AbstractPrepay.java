package com.mks.pay.service.prepay;

import java.io.File;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.mks.network.http.HttpClientHelper;
import com.mks.pay.PayDefaults;
import com.mks.pay.config.PayConfig;
import com.mks.pay.config.PayConfigException;
import com.mks.pay.config.PayConfigService;
import com.mks.utils.StringHelper;

/**
 * 抽象的预支付服务 
 */
public abstract class AbstractPrepay implements Prepay {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPrepay.class);

    private final PayConfigService configService;

    public AbstractPrepay(PayConfigService configService) {
        Preconditions.checkNotNull(configService, "configService");
        this.configService = configService;
    }
    
    protected abstract PrepayInsideRequest getPayRequest(PrepayRequest argument, PayConfig config);
    protected abstract PrepayResponse convert(byte[] array);

    @Override
    public PrepayResponse prepay(PrepayRequest argument) {
        Preconditions.checkNotNull(argument, "argument");
        argument.validate();
        Map<String, PayConfig> configs = configService.getConfigs();
        if (MapUtils.isEmpty(configs)) {
            throw new PayConfigException("pay configs is empty!");
        }
        String appId = argument.getAppId();
        PayConfig config = configs.get(appId);
        config.validate();
        File pkcs12 = config.getPkcs12();
        String appSecret = config.getAppSecret();
        String url = config.getUrlPrepay();

        String xml = getPayRequest(argument, config).toXML();

        HttpClient https = HttpClientHelper.https(pkcs12, appSecret.toCharArray());

        try {
            HttpPost post = HttpClientHelper.post(url);
            HttpClientHelper.addByteArrayEntity(post, StringHelper.convert(xml), PayDefaults.APPLICATION_XML);
            HttpResponse httpResponse = https.execute(post);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Received response statusCode {} from {}", statusCode, url);
            }
            byte[] array = HttpClientHelper.getResponse(httpResponse);
            if (ArrayUtils.isNotEmpty(array)) {
                return convert(array);
            }
            else {
                LOGGER.warn("Empty response content! from {}", url);
            }
        }
        catch (Exception e) {
            LOGGER.error("Access " + url + " occur exception!", e);
        }
        finally {
            https.getConnectionManager().shutdown();
        }
        return null;
    }
}
