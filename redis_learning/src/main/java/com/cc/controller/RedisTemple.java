package com.cc.controller;

import com.cc.bean.User;
import com.cc.service.IUserService;
import com.cc.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//
//@Controller
//@RequestMapping("redis")
public class RedisTemple {

//    @Autowired
//    private IUserService userService;
//
//    @RequestMapping("/get.do")
//    @ResponseBody
//    public String get(){
////        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
////        IUserService userService = ctx.getBean(UserServiceImpl.class);
//        return userService.getUser("application");
//    }

    @Test
    public void main(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService userService1 = ctx.getBean(UserServiceImpl.class);
        User user = userService1.getUserById("1234567");
        System.out.println(user);
        userService1.listAdd();
        List<String> list = (List<String>) userService1.listRange(2,2);
        for (String str:list){
            System.out.println(str);
        }

        System.out.println("订单消息队列");
        String cardId = "22222";
        userService1.listQueueInit(cardId);
        System.out.println("待执行任务");
        List<String> list1 = userService1.listQueueWait(cardId);
        for (String str : list1)
            System.out.println(str);
        System.out.println("开始配送");
        userService1.listQueueTouch(cardId);

        System.out.println("已完成任务");
        List<String> list2 = userService1.listQueueSucc(cardId);
        for (String str : list2)
            System.out.println(str);
    }
}
