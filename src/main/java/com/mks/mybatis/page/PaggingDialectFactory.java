package com.mks.mybatis.page;

/**
 * 分页查询方言配置工厂。 可根据不同的数据库类型返回不同的分页查询接口实现。
 *  
 */
public interface PaggingDialectFactory {

    /**
     * 根据不同的数据库类型返回不同的分页查询接口实现。
     * 
     * @param dbType 数据库类型
     * @return 分页查询方言实现 
     */
    public PaggingDialect getPaggingDialect(String dbType);
}
