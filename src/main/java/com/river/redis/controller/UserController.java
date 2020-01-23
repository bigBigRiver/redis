package com.river.redis.controller;

import com.river.redis.entity.User;
import com.river.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author river
 * 2020/1/22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/findByUserNameLike")
    @Cacheable(cacheNames = "user", key = "'river'+666", condition = "#userName.length() > 5", unless = "#result.size() == 0")
    public List<User> findByUserNameLike(String userName) {
        String name = userName + "%";
        return userService.findByUserNameLike(name);
    }

    @GetMapping("/findByUserName")
    public User findByUserName(String userName) {
        return userService.findByUserName(userName);
    }

    @GetMapping("/updatePassword")
    public User updateUser(String userName, String password) {
        User user = userService.findByUserName(userName);
        user.setPassword(password);
        return userService.saveOrUpdateUser(user);
    }

}
