package com.eu.manager.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.eu.manager.model.UserDomain;

import java.util.List;

public interface UserDao extends BaseMapper<UserDomain> {
//    int insert(UserDomain userDomain);

    List<UserDomain> selectUsers();
}
