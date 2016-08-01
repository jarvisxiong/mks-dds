package com.mks.pay.utils;

import com.mks.utils.SerializedUtils;


public final class PayId {

    public static String generate() {
        return SerializedUtils.generate();
    }
}
