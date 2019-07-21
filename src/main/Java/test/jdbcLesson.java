package test;

import dao.userDaoInterface;
import model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class jdbcLesson {
    @Test
    public void test() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("JDBCBeans02.xml");
        userDaoInterface userDao = (userDaoInterface) context.getBean("userDao");
        User user = new User();
        user.setName("dgut");
        user.setPassword("111111");
        userDao.addUser(user);
    }
}
