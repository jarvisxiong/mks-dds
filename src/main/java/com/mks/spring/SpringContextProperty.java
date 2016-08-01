package com.mks.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


public class SpringContextProperty extends PropertyPlaceholderConfigurer {

    private static Map<String, String> ctxPropertiesMap;

    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {
        super.processProperties(beanFactory, props);
        ctxPropertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
    
    public static Map<String, String> getProperties() {
        return ctxPropertiesMap;
    }

    public static String getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }
}
