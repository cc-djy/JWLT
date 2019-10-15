package com.cc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void test() {
        String key = "name";
        String result = "";
        if (redisTemplate.hasKey(key)) {
            result = redisTemplate.opsForValue().get(key);
            System.out.println("从redis中获取：" + result);
        } else {
            result = "cc";
            redisTemplate.opsForValue().set(key, result);
            System.out.println("从MySQL中获取：" + result);
        }

    }

}
