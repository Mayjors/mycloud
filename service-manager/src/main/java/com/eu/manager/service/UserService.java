package com.eu.manager.service;

import com.eu.manager.model.UserDomain;
import com.github.pagehelper.PageInfo;

public interface UserService {

    int addUser(UserDomain userDomain);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);

}
