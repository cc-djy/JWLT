package com.ssm.controller;

import com.ssm.pojo.Manager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController2 {

    @RequestMapping("/get")
    @ResponseBody
    public Manager get(){
        return new Manager();
    }

}
