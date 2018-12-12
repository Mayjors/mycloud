package com.eu.servicemybatis.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.eu.servicemybatis.dao.UserDao;
import com.eu.servicemybatis.model.UserDomain;
import com.eu.servicemybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(UserDomain userDomain) {
        return userDao.insert(userDomain);
    }

    @Override
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = null;
        String str = stringRedisTemplate.opsForList().index("user:list", 1);
        if (str == null) {
            userDomains = userDao.selectUsers();
            // 具体使用
            redisTemplate.opsForList().leftPush("user:list", JSON.toJSONString(userDomains));
            stringRedisTemplate.opsForValue().set("user:name", "张三");
        } else {
            userDomains  = (List<UserDomain>) JSONArray.parseArray(str, UserDomain.class);
        }
        PageInfo result = new PageInfo(userDomains);
        return result;
    }
}
