package com.eu.mutildatabase.dao;

import com.eu.mutildatabase.model.UserDomain;

import java.util.List;

/**
 * @author yuanjie
 * @date 2019/1/7 18:41
 */
public interface UserDao {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();
}
