package com.mks.mq;

import com.mks.utils.JsonHelper;


public class MessageHelper {

    public static byte[] as(Object o) {
        return JsonHelper.convert2bytes(o);
    }
    
    public static <T> T as(byte[] body, Class<T> cls) {
        return JsonHelper.convert(body, cls);
    }
}
