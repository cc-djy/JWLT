package com.cc.controller;

import com.cc.pojo.Student;
import com.cc.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stuController")
public class StudentController {
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "stu/register";
    }

    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    @ResponseBody
    public Student register(@RequestBody Student student){
        System.out.println(student);
        return student;
    }

    @Test
    public void main(){
        User user = new User("cc","123456",21);
        System.out.println(user);
        Student stu = new Student();
        stu.setName("cc");
        stu.setPassword("123456");
        System.out.println(stu);
    }
}
