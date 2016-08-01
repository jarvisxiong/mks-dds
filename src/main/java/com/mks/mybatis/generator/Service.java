package com.mks.mybatis.generator;

import java.util.List;

import com.mks.mybatis.page.PageInfo;

public abstract class Service<T, Condition, PK, DAO extends Dao<T, Condition, PK>> implements Dao<T, Condition, PK> {

    protected abstract DAO getDao();

    @Override
    public int countByCondition(Condition condition) {
        return getDao().countByCondition(condition);
    }

    @Override
    public void deleteByCondition(Condition condition) {
        getDao().deleteByCondition(condition);
    }

    @Override
    public void deleteByPrimaryKey(PK id) {
        getDao().deleteByPrimaryKey(id);
    }

    @Override
    public void insert(T record) {
        getDao().insert(record);
    }

    @Override
    public void insertSelective(T record) {
        getDao().insertSelective(record);
    }

    @Override
    public List<T> selectByCondition(Condition condition) {
        return getDao().selectByCondition(condition);
    }

    @Override
    public List<T> selectByCondition(PageInfo<T> pageInfo, Condition condition) {
        return getDao().selectByCondition(pageInfo, condition);
    }

    @Override
    public T selectByPrimaryKey(PK id) {
        return getDao().selectByPrimaryKey(id);
    }

    @Override
    public void updateByConditionSelective(T record, Condition condition) {
        getDao().updateByConditionSelective(record, condition);
    }

    @Override
    public void updateByCondition(T record, Condition condition) {
        getDao().updateByCondition(record, condition);
    }

    @Override
    public void updateByPrimaryKeySelective(T record) {
        getDao().updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(T record) {
        getDao().updateByPrimaryKey(record);
    }

}
