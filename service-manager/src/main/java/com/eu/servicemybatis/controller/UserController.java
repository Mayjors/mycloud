package com.eu.servicemybatis.controller;

import com.eu.servicemybatis.model.UserDomain;
import com.eu.servicemybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public int addUser(UserDomain user) {
        if (user == null) {
            user = new UserDomain();
            user.setUserName("mmm");
            user.setPassword("123");
            user.setPhone("123");
        }
        return userService.addUser(user);
    }

    @RequestMapping("/all")
    public Object findAllUser(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }
}
