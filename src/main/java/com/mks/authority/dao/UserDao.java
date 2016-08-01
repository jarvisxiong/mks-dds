package com.mks.authority.dao;

import java.util.List;

import com.mks.authority.entity.User;
import com.mks.authority.entity.UserSeletive;
import com.mks.mybatis.support.IMyBatisDao;


public interface UserDao extends IMyBatisDao<User, Long> {

    List<User> select(UserSeletive seletive);
}
