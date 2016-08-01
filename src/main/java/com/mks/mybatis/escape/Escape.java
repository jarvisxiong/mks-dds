package com.mks.mybatis.escape;

/**
 * 转义接口。
 * 不同类型的数据库需要实现该接口。
 * 
 * <pre>
 * 
 * EscapeFactory escapeFactory = SpringContextHolder.getBean(EscapeFactory.class);
 * 
 * String dbType = DataBaseTypes.getDataSourceType(SpringContextHolder.getCurrentDataSource());
 * 
 * Escape escape = escapeFactory.getEscape(dbType);
 * </pre>
 *  
 */
public interface Escape {

    char[] ESCAPE_CHAR = { '%', '_', '^' };

    String escape(String value);

    String unescape(String value);

}
