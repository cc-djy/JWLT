package test;

import Service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test103 {

    @Test
    public void test102(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bankTraning4.xml");
        IAccountService accountService = (IAccountService) context.getBean("accountService");
        accountService.transferMoney("cc","djy",100);
    }
}
