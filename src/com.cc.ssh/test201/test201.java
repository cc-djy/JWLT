package test201;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sshModel.User;
import sshService.IUserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-hibernate-context.xml")
public class test201 {
    @Autowired
    private IUserService userService;
    @Test
    public void test201(){
        User user = new User("djy","123",18);
        userService.register(user);
    }
}
