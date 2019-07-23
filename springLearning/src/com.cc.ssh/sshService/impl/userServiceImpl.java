package sshService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sshDao.IUserDao;
import sshDao.impl.userDaoImpl;
import sshModel.User;
import sshService.IUserService;
@Service
public class userServiceImpl implements IUserService {
    private IUserDao userDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void register(User user) {
        userDao.add(user);
    }

}
