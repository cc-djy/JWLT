package model;

import javax.annotation.PostConstruct;

public class User {
    private String name;
    private String password;

    @Override
    public String toString() {
        return "user{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    @PostConstruct //自定义初始化
    public void myInit(){
        System.out.println("自定义初始化方法。。。。。");
        this.name = "cc";
        this.password = "123456";
    }
}
