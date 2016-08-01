package com.mks.mybatis.escape;


/**
 * 转义工厂。 
 */
public interface EscapeFactory {

    /**
     * <p>
     * 根据数据库类型获取该数据库转义的方言。
     * </p>
     *
     * @param dbType
     * @return
     */
    Escape getEscape(String dbType);
}
