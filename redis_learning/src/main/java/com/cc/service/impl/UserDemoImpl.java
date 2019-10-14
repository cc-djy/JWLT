package com.cc.service.impl;

import com.cc.bean.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserDemoImpl {

    @Cacheable(cacheNames = "userCache" ,key = "#key")
    public User findUserByKey(String key){
        System.out.println("从MySql数据库查找");
        User user = new User();
        user.setId("123");
        user.setUsername("cc");
        user.setSex("男");
        user.setAge("20");
        user.setPassword("123");
        return user;
    }
}
