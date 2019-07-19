package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.userServiceInterface;

public class test {

    @Test
    public void test() throws Exception{
//
//        //第一天学习spring Ioc和注释
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        UserAction userAction = context.getBean(UserAction.class);
//        UserAction userAction2 = context.getBean(UserAction.class);
//        System.out.println(userAction);
//        User user = new User();
//        userAction.save(user);
//        context.getClass().getMethod("close").invoke(context);

        userServiceInterface userService = (userServiceInterface) context.getBean("serviceProxy");

//        userServiceInterface userService = UserServiceAspectFactory.createUserService();
        userService.deleteUser(1);
        userService.addUser("chenchao");
        userService.updateUser();
        System.out.println("这是test");
//        User user = new User();
//        userService.addUserService(user);
    }
}
