package sshAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import sshModel.User;
import sshService.IUserService;

@Controller
public class userAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    @Autowired
    private static IUserService userService = (IUserService) new ClassPathXmlApplicationContext("new-spring-hibernate-context.xml").getBean("userService");

//    public void setUserService(IUserService userService) {
//        this.userService = userService;
//    }
    public String register(){
        user = new User(1,"djy","123",18);
        System.out.println(user);
        userService.register(user);
        return "success";
    }
    @Test
    public void main(){
        this.register();
    }
    @Override
    public User getModel() {
        return user;
    }
}
