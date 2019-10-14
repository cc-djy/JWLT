package com.cc.controller;

import com.cc.bean.User;
import com.cc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * @param username 用户名
     * @param password 密码
     * @param valCode  验证码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", produces = "application/json;charset=utf-8")
    public String login(@RequestParam(name = "username") final String username,
                        @RequestParam(name = "password") final String password,
                        @RequestParam(name = "valCode", required = false) final String valCode) {
        /**
         * 1.验证码比较
         * 2.执行登录功能
         * 2.1判断是否配限制登录
         */
        Map<String, Object> map = userService.loginLock(username);
        if ((boolean) map.get("flag")) { //被限制登录
            return "登录失败，因" + username + "用户超过限制登陆次数，需要等待：" + map.get("lockTime") + "分钟，才能再次登录。";
        } else { //没有被限制登录
            //执行登录功能
            User user = userService.login(username, password);
            //判断是否登录成功
            if (user != null) { //登录成功
                //清空该用户对应的所有的key
                return "success.jsp";

            } else { //登录不成功
                String result = userService.loginValData(username);
                return result;
            }
        }
    }
}
