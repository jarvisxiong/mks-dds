package com.mks.mybatis;


/**
 * 线程局部变量模拟请求上下文。 
 */
public class SpObserver {

    /** 线程局部变量模拟请求上下文, 用于多数据源切换时映射的数据源标识. */
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    
    /**
     * 添加数据源标识。
     * 
     * @param dsFlag 
     */
    public static void putDsFlag(String dsFlag) {
        threadLocal.set(dsFlag);
    }
    
    /**
     * 获得数据源标识。 
     */
    public static String getDsFlag() {
        return threadLocal.get();
    }
    
    /**
     * 移除数据源标识。 
     */
    public static void clearDsFlag() {
        threadLocal.remove();
    }
}
