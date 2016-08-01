package com.mks.authority.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.mks.authority.dao.UserDao;
import com.mks.authority.entity.User;
import com.mks.authority.entity.UserSeletive;
import com.mks.mybatis.support.MyBatisServiceSupport;


public class UserServiceImpl extends MyBatisServiceSupport<User, Long, UserDao> implements UserService {

    private UserDao userDao;
    
    @Override
    public User select(String loginName) {
        UserSeletive seletive = new UserSeletive();
        seletive.setLoginName(loginName);
        List<User> rs = userDao.select(seletive);
        return CollectionUtils.isNotEmpty(rs) ? rs.get(0) : null;
    }

    @Override
    public User select(String loginName, String password) {
        UserSeletive seletive = new UserSeletive();
        seletive.setLoginName(loginName);
        seletive.setPassword(password);
        List<User> rs = userDao.select(seletive);
        return CollectionUtils.isNotEmpty(rs) ? rs.get(0) : null;
    }

    @Override
    protected UserDao getDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
