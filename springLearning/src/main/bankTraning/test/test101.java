package test;

import Service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test101 {

    @Test
    public void test101(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bankTraning2.xml");
        IAccountService accountService = (IAccountService) context.getBean("proxyService");
        accountService.transferMoney("cc","djy",100);
    }
}
