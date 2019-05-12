package dao;

import model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class UserDao implements userDaoInterface{
    @Override
    public void addUserDao(User u){
        System.out.println("Dao添加用户" + u.toString());
    }

    @PostConstruct //自定义初始化
    public void myInit(){
        System.out.println("自定义初始化方法。。。。。");

    }
}
