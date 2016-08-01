package com.mks.mybatis.support;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * MyBatis Dao 基类支持。 
 */
public class MyBatisDaoSupport {

    /** MyBatis Session 工厂 */
    protected SqlSessionFactory sqlSessionFactory;
    
    // -------------------------------- 以下为Getter/Setter方法 -------------------------------- //

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

}
