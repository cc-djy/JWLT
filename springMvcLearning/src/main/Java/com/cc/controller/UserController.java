package com.cc.controller;

import com.cc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping("/addUser")
    public String addUser(){
        return "user/addUser";
    }
    @RequestMapping("/showUser")
    public ModelAndView showUser(User user){
        System.out.println(user.toString());
        ModelAndView mv = new ModelAndView("user/userList");
        mv.addObject("User",user);
        return mv;
    }

}
