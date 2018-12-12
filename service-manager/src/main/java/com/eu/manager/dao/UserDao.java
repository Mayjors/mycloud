package com.eu.manager.dao;

import com.eu.manager.model.UserDomain;
import java.util.List;

public interface UserDao {
    int insert(UserDomain userDomain);

    List<UserDomain> selectUsers();
}
