package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.userServiceInterface;

public class lesson {
    @Test
    public void test() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("lessonBeans.xml");

        userServiceInterface userService = (userServiceInterface) context.getBean("userService");

        userService.deleteUser(1);
    }
}
