package com.eu.manager.service.impl;

import com.eu.manager.model.UserDomain;
import com.eu.manager.mq.sender.MessageSender;
import com.eu.manager.service.TestService;
import com.eu.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanjie
 * @date 2019/1/3 20:04
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl implements TestService {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MessageSender messageSender;

    @Override
    public void test() {
        UserDomain user = new UserDomain();
        user.setUserName("xxxx");
        user.setPassword("123");
        user.setPhone("123");
        userService.addUser(user);
        redisTemplate.opsForValue().set("user:"+ user.getUserName(), "user");
        messageSender.sendInfo(user.getUserName());

        System.out.println("sdsds");
        System.out.println("sdsd");
    }
}
