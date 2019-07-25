package com.cc.controller;

import com.cc.pojo.User;
import com.cc.pojo.UserExt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/userController")
public class UserController{

    @RequestMapping("/getUser")
    public ModelAndView getUser(@RequestParam("id") Long id){
        System.out.println(id);
        ModelAndView mv = new ModelAndView("user/userList");
        mv.addObject("name","cc");
        return mv;
    }
    @RequestMapping("/toAddUser")
    public String toAddUser(){
        return "user/addUser";
    }
    @RequestMapping("/addUser")
    public String addUser(User user){
        System.out.println(user.toString());
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("User",user);
        return "user/userList";
    }

    @RequestMapping("/addUser2")
    public String addUser2(UserExt userExt){
        System.out.println("ext"+userExt.getUser());
        return "user/userList";
    }

    @RequestMapping("/addUser3")
    public String addUser3(UserExt userExt){
        System.out.println("list"+userExt.getUserList());
        return "user/userList";
    }

    @RequestMapping("/addUser4")
    public String addUser4(UserExt userExt){
        System.out.println("map"+userExt.getUserMap());
        return "user/userList";
    }
}
