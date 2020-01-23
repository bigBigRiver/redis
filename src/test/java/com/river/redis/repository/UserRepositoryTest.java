package com.river.redis.repository;

import com.river.redis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void save() {
        User user = new User("river4","123456");
        User user1 = userRepository.save(user);
        log.info("id:{}, userName:{}, password:{}",user1.getId(),user1.getUserName(),user1.getPassword());
    }

    @Test
    void findByUserName() {
        User user = userRepository.findByUserName("river");
        assertNotEquals(user,null);
        log.info(user.getPassword());
    }

    @Test
    void update(){
        User user = userRepository.findByUserName("river");
        user.setPassword("123123");
        userRepository.save(user);
        assertNotEquals(user,null);
        log.info(user.getPassword());
    }

    @Test
    void findByUserNameEndsWith() {
        List<User> userList = userRepository.findByUserNameEndsWith("ver3");
        assertEquals(userList.size(),1);
        log.info(userList.get(0).getPassword());
    }

    @Test
    void findByUserNameStartsWith() {
        List<User> userList = userRepository.findByUserNameStartsWith("river");
        assertNotEquals(userList.size(), 0);
    }

    @Test
    void findByUserNameContains() {
        List<User> userList = userRepository.findByUserNameContains("3");
        assertEquals(userList.size(),1);
        log.info(userList.get(0).getPassword());
    }

    @Test
    void findByUserNameLike() {
        List<User> userList = userRepository.findByUserNameLike("river%");
        assertNotEquals(userList.size(), 0);
        log.info("size:{}",userList.size());
        userList = userRepository.findByUserNameLike("river_");
        assertNotEquals(userList.size(), 0);
        log.info("size:{}",userList.size());
    }

    @Test
    void findByUserNameNotIn() {
        String[] userNames = {"river", "river1", "river4"};
        List<User> userList = userRepository.findByUserNameNotIn(userNames);
        assertNotEquals(userList.size(), 0);
        log.info("size:{}",userList.size());
    }

    @Test
    void findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");//创建时间降序排序
        PageRequest pageRequest = PageRequest.of(0,3,sort);//查询第一页，一页3条记录

        Page<User> page = userRepository.findAll(pageRequest);
        List<User> userList = page.getContent();
        assertEquals(userList.size(), 3);
    }

    @Test
    void findByUserName1() {
        PageRequest pageRequest = PageRequest.of(0, 3);//查询第一页，一页3条记录
        Page<User> page = userRepository.findByUserNameLike("river%",pageRequest);
        List<User> userList = page.getContent();
        assertEquals(userList.size(), 3);
    }


    @Test
    void findByUserNameLikeAndCreateTimeGreaterThan() throws ParseException {
        String dateString = "2020-01-13 21:53:57";
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(strDateFormat);
        Date date = df.parse(dateString);
        List<User> userList = userRepository.findByUserNameLikeAndCreateTimeGreaterThan("river%", date);
        assertNotEquals(userList.size(), 0);
        for (User user : userList) {
            log.info(user.getUserName());
        }
    }
}