package com.mks.utils;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public final class GsonHelper {

    private GsonHelper() {
    }
    
    
    public static <T> T convert(String string, Class<T> cls) {
        return convert(StringHelper.convert(string), cls);
    }
    
    /**
     * 将data转换成指定类型的对象
     * 
     * @param <T>
     * @param data
     * @param cls
     * @return 可能为<code>null</code>
     */
    public static <T> T convert(byte[] data, Class<T> cls) {
        String str = StringHelper.convert(data);
        if (StringUtils.isNotBlank(str))
            return new Gson().fromJson(str, cls);
        return null;
    }
    
    public static byte[] convert(Object object, boolean withoutExposeAnnotation) {
        if (null == object)
            return null;
        
        Gson gson = null;
        if (withoutExposeAnnotation) {
            gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        }
        else {
            gson = new Gson();
        }
        String str = gson.toJson(object);
        if (StringUtils.isBlank(str))
            return null;
        
        return StringHelper.convert(str);
    }
    
    public static String convert2String(Object object) {
        return StringHelper.convert(convert(object));
    }
    
    public static byte[] convert(Object object) {
        return convert(object, false);
    }
}
