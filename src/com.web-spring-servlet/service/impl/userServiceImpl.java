package service.impl;

import service.IUserService;

public class userServiceImpl implements IUserService {
    @Override
    public void addUser(Object user) {
        System.out.println("添加用户成功---------"+user);
    }
}
