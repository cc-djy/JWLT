package test;

import Dao.IAccountDao;
import Service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test100 {

    @Test
    public void test100(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bankTraning.xml");
        IAccountService accountService = (IAccountService) context.getBean("accountService");
        accountService.transferMoney("cc","djy",50);
    }
}
