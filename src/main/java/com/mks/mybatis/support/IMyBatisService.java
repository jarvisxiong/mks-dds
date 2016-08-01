package com.mks.mybatis.support;

import java.io.Serializable;

/**
 * <p>
 * 业务层的基类接口。
 * </p>
 * 该接口封装了一些基础公共的处理方法，比如CURD，分页查询，指定SQL语句查询等。 
 * 如果需要添加其他业务处理方法，请在子接口中添加。
 * 
 * @param <T> {@link java.lang.Object}
 * @param <PK> {@link com.skymobi.webframework.orm.mybatis.support.IMyBatisDao} 
 */
public interface IMyBatisService<T, PK extends Serializable> extends IMyBatisDao<T, PK> {

}
