package com.ssm.controller;

import com.ssm.pojo.Manager;
import com.ssm.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController2 {
    @Autowired
    private IManagerService managerService;
    @RequestMapping("/get")
    @ResponseBody
    public Object get() throws Exception {
        System.out.println("come in");
        Manager manager = managerService.loginService(3);
        if (manager == null)
            return false;
        System.out.println(manager);
        List<Manager> managers = managerService.getManagerList();
        System.out.println(managers);
        return manager;
    }



}
