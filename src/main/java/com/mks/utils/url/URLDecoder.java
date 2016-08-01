package com.mks.utils.url;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mks.utils.Argument;


 
public final class URLDecoder {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(URLDecoder.class);

    /**
     * 将一个URL解码转换成对象。
     * 
     * @param <T>
     * @param url
     * 支持的URL格式：
     * <ul>
     * <li>http://www.big-mouth.cn/nvwa-utils/xml/decoder?id=123&json_data=bbb</li>
     * <li>/nvwa-utils/xml/decoder?id=123&json_data=bbb</li>
     * <li>id=123&json_data=bbb</li>
     * </li>
     * </ul>
     * @param cls
     * @return
     * @throws Exception 
     */
    public static <T> T decode(String url, Class<T> cls) throws Exception {
        if (StringUtils.isBlank(url))
            return null;
        T t = cls.newInstance();
        
        String string = null;
        if (StringUtils.contains(url, "?")) {
            string = StringUtils.substringAfter(url, "?");
        }
        else {
            int start = StringUtils.indexOf(url, "?") + 1;
            string = StringUtils.substring(url, start);
        }
        
        Map<String, Object> attrs = Maps.newHashMap();
        String[] kvs = StringUtils.split(string, "&");
        for (String kv : kvs) {
            String[] entry = StringUtils.split(kv, "=");
            if (entry.length <= 1) {
                continue;
            }
            else if (entry.length == 2) {
                attrs.put(entry[0], entry[1]);
            }
            else {
                List<String> s = Lists.newArrayList(entry);
                s.remove(0);
                attrs.put(entry[0], StringUtils.join(s.toArray(new String[0]), "="));
            }
        }
        
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            // if the field name is 'appId'
            String fieldName = field.getName();
            String paramName = fieldName;
            if (field.isAnnotationPresent(Argument.class)) {
                paramName = field.getAnnotation(Argument.class).name();
            }
            // select appId node
            Object current = attrs.get(paramName);
            if (null == current) {
                // select appid node
                current = attrs.get(paramName.toLowerCase());
            }
            if (null == current) {
                // select APPID node
                current = attrs.get(paramName.toUpperCase());
            }
            if (null == current) {
                // select app_id node
                String nodename = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(paramName), "_").toLowerCase();
                current = attrs.get(nodename);
            }
            if (null == current) {
                // select APP_ID node
                String nodename = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(paramName), "_").toUpperCase();
                current = attrs.get(nodename);
            }
            if (null != current) {
                String invokeName = StringUtils.join(new String[] { "set", StringUtils.capitalize(fieldName) });
                try {
                    MethodUtils.invokeMethod(t, invokeName, current);
                }
                catch (NoSuchMethodException e) {
                    LOGGER.warn("NoSuchMethod-" + invokeName);
                }
                catch (IllegalAccessException e) {
                    LOGGER.warn("IllegalAccess-" + invokeName);
                }
                catch (InvocationTargetException e) {
                    LOGGER.warn("InvocationTarget-" + invokeName);
                }
            }
        }
        return t;
    }
}
