package service;

import dao.userDaoInterface;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements userServiceInterface{
    @Autowired
    private userDaoInterface ud;

    @Override
    public void addUserService(User u){
        System.out.println("添加用户" + u);
        ud.addUserDao(u);
    }
    @Override
    public String addUser(String name) {
        System.out.println("添加用户......" );
        return name;
    }

    @Override
    public Object deleteUser(int id) {
        System.out.println("删除用户.....");
        return id;
    }

    @Override
    public void updateUser() {
        System.out.println("更新用户");
    }

}
