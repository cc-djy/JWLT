package sshAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import sshModel.User;
import sshService.IUserService;

public class userAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    @Autowired
    private IUserService userService;
    public String registering(){
        System.out.println(user);
        userService.register(user);
        return "success";
    }

    @Override
    public User getModel() {
        return user;
    }
}
