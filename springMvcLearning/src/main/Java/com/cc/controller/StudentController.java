package com.cc.controller;

import com.cc.pojo.Student;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/register")
    public @ResponseBody Student register(Student student){
//        model.addAttribute("student",student);
        System.out.println(student);
        return student;
    }
}
