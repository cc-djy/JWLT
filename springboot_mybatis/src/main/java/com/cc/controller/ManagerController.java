package com.cc.controller;

import com.cc.mapper.ManagerMapper;
import com.cc.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private ManagerMapper managerMapper;

    @RequestMapping("/queryManagers")
    @ResponseBody
    public List<Manager> queryManagers(){
        return managerMapper.queryManagers();
    }
}
