package com.mks.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 声明该字段作为参数需要特殊处理
 * 
 * @author mks
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Argument {

    /**
     * 名称 
     */
    String name();
}
