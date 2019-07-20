package service;

import model.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements userServiceInterface {

//    @Autowired
//    private userDaoInterface ud;

    @Override
    public void addUserService(User u){
        System.out.println("添加用户" + u);
    }

    @Override
    public String addUser(String name) {
        System.out.println("添加用户......");
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
