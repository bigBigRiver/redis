package com.river.redis.service.impl;

import com.river.redis.entity.User;
import com.river.redis.repository.UserRepository;
import com.river.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findByUserNameLike(String userName) {
        return userRepository.findByUserNameLike(userName);
    }

    @Override
    @Cacheable(key = "'userInfo'")
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    @CacheEvict(key = "'userInfo'")
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }
}
