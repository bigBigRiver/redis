package com.river.redis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {

    private static final long serialVersionUID = -4359415715810923866L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*配置之后返回的id不为0*/
    private int id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @JsonIgnore
    @Column(name="create_time")
    private Date createTime;

    @JsonIgnore
    @Column(name="update_time")
    private Date updateTime;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public User(){

    }
}
