package com.mks.pay.ability;


/**
 * 可从XML字符串转换成对象 
 */
public interface FromXMLWrapper<T> {

    T fromXML(String xml);
}
