package com.river.redis.service;

import com.river.redis.entity.User;

import java.util.List;

public interface UserService {
    List<User> findByUserNameLike(String userName);

    User findByUserName(String userName);

    User saveOrUpdateUser(User user);
}
