package com.mks.zookeeper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 声明该方法需要使用分布式同步处理。
 *  
 * @see com.mks.zookeeper.annotation.PrimaryKey
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SynchronousSupport {

    /**
     * 锁的路径
     * <pre>
     * e.g. "/project/synchronous/0000001"
     * </pre>
     * @return
     */
    String zkPath();
}
