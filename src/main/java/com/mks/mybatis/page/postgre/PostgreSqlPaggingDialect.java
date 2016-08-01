package com.mks.mybatis.page.postgre;


import com.mks.mybatis.page.DefaultPaggingDialect;


/**
 * PostgreSql 数据库分页查询方言。
 *  
 */
public class PostgreSqlPaggingDialect extends DefaultPaggingDialect {

    @Override
    public String getPaggingSql(String querySql, int pageNo, int pageSize) {
        int myPageNo = (pageNo > 0 ? pageNo : 1);
        int myPageSize = (pageSize > 0 ? pageSize : 10);
        int begin = (myPageNo - 1) * myPageSize;

        String sql = querySql.trim() + " OFFSET " + begin + " LIMIT " + myPageSize;
        return sql;
    }
}
