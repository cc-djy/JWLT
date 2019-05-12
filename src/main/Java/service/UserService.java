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
        System.out.println("service添加用户" + u);
        ud.addUserDao(u);
    }
}
