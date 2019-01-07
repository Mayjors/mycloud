package com.eu.mutildatabase.service;

import com.eu.mutildatabase.model.UserDomain;
import com.github.pagehelper.PageInfo;

/**
 * @author yuanjie
 * @date 2019/1/7 18:42
 */
public interface UserService {

    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
}
