package com.eu.servicemybatis.service;

import com.eu.servicemybatis.model.UserDomain;
import com.github.pagehelper.PageInfo;

public interface UserService {

    int addUser(UserDomain userDomain);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);

}
