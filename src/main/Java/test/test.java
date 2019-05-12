package test;

import action.UserAction;
import model.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

    @Test
    public void test() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserAction userAction = context.getBean(UserAction.class);
        UserAction userAction2 = context.getBean(UserAction.class);
        System.out.println(userAction);
        User user = new User();
        userAction.save(user);
        context.getClass().getMethod("close").invoke(context);
    }
}
