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
//        userDaoInterface userDao = (userDaoInterface) context.getBean("userDao");
//        User user =new User();
//        userDao.addUser(user);
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/springJDBC");
//        dataSource.setUsername("root");
//        dataSource.setPassword("123456");
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        jdbcTemplate.execute("select * from spring");
//        jdbcTemplate.update("insert into spring(userNAME, PASSWORD) value (?,?)","cc","123456");
    }
}
