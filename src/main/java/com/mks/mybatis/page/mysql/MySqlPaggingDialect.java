package com.mks.mybatis.page.mysql;

import com.mks.mybatis.page.DefaultPaggingDialect;

/**
 * MySql分页方言. 注意MySQL的分页方式, limit值含义: 起始行(从0开始), 返回行数,
 * 
 * <pre>
 *  SELECT * FROM ( 
 *    select * from myLargeTable 
 *  ) LIMIT 0, 10;
 * </pre>
 *   
 */
public class MySqlPaggingDialect extends DefaultPaggingDialect {

    @Override
    public String getPaggingSql(String querySql, int pageNo, int pageSize) {
        int myPageNo = (pageNo > 0 ? pageNo : 1);
        int myPageSize = (pageSize > 0 ? pageSize : 10);
        int begin = (myPageNo - 1) * myPageSize;

        String sql = querySql.trim() + " limit " + begin + " ," + myPageSize;
        return sql;
    }
}
