package com.eu.servicemybatis.dao;

import com.eu.servicemybatis.model.UserDomain;
import java.util.List;

public interface UserDao {
    int insert(UserDomain userDomain);

    List<UserDomain> selectUsers();
}
