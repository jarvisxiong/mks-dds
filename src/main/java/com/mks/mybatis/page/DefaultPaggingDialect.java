package com.mks.mybatis.page;

import java.util.regex.Pattern;

import org.apache.ibatis.session.RowBounds;


/**
 * 默认（不分页）分页查询方言。
 *  
 */
public class DefaultPaggingDialect implements PaggingDialect {

    protected static final Pattern ORDERBY = Pattern.compile("order\\s+by\\s+[^,\\s]+(\\s+(asc|desc))?(\\s*,\\s*[^,\\s]+(\\s+(asc|desc))?\\s*)*", Pattern.CASE_INSENSITIVE);
    protected static final String EMPTY = "";
    
    /**
     * (non-Javadoc)
     * 
     * @see com.skymobi.commons.dao.page.PaggingDialect#getCountSql(java.lang.String)
     */
    @Override
    public String getCountSql(String querySql) {
        querySql = ORDERBY.matcher(querySql).replaceAll(EMPTY);
        return "SELECT COUNT(0) " + RowBounds.COUNT_COLUMN_ALIAS + " FROM (" + querySql + ") AS COUNT_TABLE";
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.skymobi.commons.dao.page.PaggingDialect#getPaggingSql(java.lang.String, int, int)
     */
    @Override
    public String getPaggingSql(String querySql, int pageNo, int pageSize) {
        return querySql;
    }

}
