package com.cc.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class User implements Serializable {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String password;
    private String age;
    private String sex;

    public static synchronized String getKeyName() {
        return "user:";
    }

    /**
     * 锁定限制登录key：user:loginTime:lock:用户名
     * @return
     */
    public static synchronized String getLoginTimeLockKey(String username) {
        return "user:loginTime:lock:" + username;
    }

    /**
     * 生成登录失败次数的key
     * @param username
     * @return
     */
    public static synchronized String getLoginFalseTimeKey(String username) {
        return "user:loginCount:fail:" + username;
    }

    public User() {
    }

    public User(String id, String username, String password, String age, String sex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
