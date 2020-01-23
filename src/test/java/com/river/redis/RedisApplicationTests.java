package com.river.redis;

import com.river.redis.entity.User;
import com.river.redis.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@Slf4j
@SpringBootTest
class RedisApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void testSomething() {
        User user = new User("engineerdong","123456");
        User user1 = userRepository.save(user);
        log.info("id:{}, userName:{}, password:{}",user1.getId(),user1.getUserName(),user1.getPassword());
    }

    @Test
    void testFindByUserName(){
    }

}
