package com.river.redis.repository;

import com.river.redis.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


/**
 * @author river
 * 2020/1/21
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String userName);

    List<User> findByUserNameEndsWith(String userName);

    List<User> findByUserNameStartsWith(String userName);

    List<User> findByUserNameContains(String userName);

    List<User> findByUserNameLike(String userName);

    List<User> findByUserNameNotIn(String[] userNames);

    @Override
    Page<User> findAll(Pageable pageable);

    /*原生查询，列名和表名使用数据库中的字段和表名*/
    @Query(value = "select * from user where user_name like ?1",
            countQuery = "select count(*) from user where user_name like ?1",nativeQuery = true)
    Page<User> findByUserNameLike(String userName, Pageable pageable);

    /*hql语句，列名和表名使用实体类的属性和类名，如果没有new User()，返回值List中是Object[]，而不是User实体类*/
    @Query(value = "select new User(u.userName, u.password) from User u where u.userName like :userName and u.createTime > :createTime")
    List<User> findByUserNameLikeAndCreateTimeGreaterThan(@Param("userName")String userName, @Param("createTime") Date createTime);
}
