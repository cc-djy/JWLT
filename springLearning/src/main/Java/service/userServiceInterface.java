package service;

import sshModel.User;

public interface userServiceInterface {
    public void addUserService(User u);
    public String addUser(String name);
    public Object deleteUser(int id);
    public void updateUser();
}
