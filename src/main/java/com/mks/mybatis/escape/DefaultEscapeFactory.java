package com.mks.mybatis.escape;

import org.apache.commons.lang.StringUtils;

import com.mks.mybatis.DbTypes;


/**
 * 默认转义工厂实现。
 *  
 */
public class DefaultEscapeFactory implements EscapeFactory {

    /**
     * (non-Javadoc)
     * @see com.skymobi.webframework.orm.mybatis.escape.EscapeFactory#getEscape(java.lang.String)
     */
    @Override
    public Escape getEscape(String dbType) {
        Escape escape = null;
        if (StringUtils.equals(dbType, DbTypes.POSTGRESQL)) {
            escape = new PostgreSQLEscape();
        }
        else if (StringUtils.equals(dbType, DbTypes.ORACLE)) {
            escape = new OracleEscape();
        }
        return escape;
    }

}
