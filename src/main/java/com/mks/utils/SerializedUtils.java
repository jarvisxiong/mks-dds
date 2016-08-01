package com.mks.utils;

import org.apache.commons.lang.StringUtils;
 
public class SerializedUtils {

    private static int seed = 0;
    private static final Object object = new Object();
    private static final String PATTERN = "yyyyMMddHHmmss";

    public static String genAbstract() {
        return genAbstract("O", PATTERN);
    }
    
    public static String genAbstract(String prefix) {
        return genAbstract(prefix, PATTERN);
    }
    
    public static String genAbstract(String prefix, String pattern) {
        int tmpCount = 1;
        synchronized (object) {
            seed++;
            if (seed > 999) {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    ;
                }
                seed = 1;
            }
            tmpCount = seed;
        }
        String strCount = "00" + tmpCount;
        strCount = strCount.substring(strCount.length() - 3);
        String strtime = DateUtils.getCurrentStringDate(pattern);
        return prefix + strtime + strCount;
    }
    
    private static final Object object2 = new Object();
    private static long seed2 = 0;
    
    public static String generate() {
        long tmp = 1;
        synchronized (object2) {
            seed2++;
            if (seed2 > 999999999999999l) {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    ;
                }
                seed2 = 1;
            }
            tmp = seed2;
        }
        return DateUtils.getCurrentStringDate("yyyyMMddHHmmssSSS") + StringUtils.leftPad(String.valueOf(tmp), 15, "0");
    }
}
