package com.ssm.controller;

import com.ssm.pojo.Manager;
import com.ssm.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController2 {
    @Autowired
    private IManagerService managerService;
    @RequestMapping("/get")
    @ResponseBody
    public Manager get() throws Exception {
        System.out.println("come in");
        Manager manager = managerService.loginService("cc");
        System.out.println(manager);
        return manager;
    }



}
