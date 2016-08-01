package com.mks.mybatis.page;

import com.mks.mybatis.DbTypes;
import com.mks.mybatis.page.mysql.MySqlPaggingDialect;
import com.mks.mybatis.page.oracle.OraclePaggingDialect;
import com.mks.mybatis.page.postgre.PostgreSqlPaggingDialect;


/**
 * 分页查询方言工厂的默认实现。<br/>
 * 目前支持的数据库有：
 * <ul>
 * <li>Oracle</li>
 * <li>PostgreSql</li>
 * <li>MySQL</li>
 * </ul>
 * 
 * @author Allen.Hu / 2012-6-29
 * @since SkyMarket 1.0
 */
public class DefaultPaggingDialectFactory implements PaggingDialectFactory {

    @Override
    public PaggingDialect getPaggingDialect(String dbType) {
        PaggingDialect dialect = null;
        if (DbTypes.ORACLE.equals(dbType)) {
            dialect = new OraclePaggingDialect();
        }
        else if (DbTypes.POSTGRESQL.equals(dbType)) {
            dialect = new PostgreSqlPaggingDialect();
        }
        else if (DbTypes.MYSQL.equals(dbType)) {
            dialect = new MySqlPaggingDialect();
        }
        return dialect;
    }
}
