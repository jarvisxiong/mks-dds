package com.mks.authority.service;

import com.mks.authority.entity.User;


public interface UserService {

    User select(String loginName);
    
    User select(String loginName, String password);
}
