package com.cc;

import com.cc.mapper.ManagerMapper;
import com.cc.pojo.Manager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class MybatisTest {
    @Autowired
    private ManagerMapper managerMapper;
    @Test
    public void test(){
        System.out.println(managerMapper.queryManagers());
    }
}
