package com.cc.controller;

import com.cc.bean.User;
import com.cc.service.IUserService;
import com.cc.service.impl.UserDemoImpl;
import com.cc.service.impl.UserServiceImpl;
import com.cc.util.JedisPoolUtil;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class redisClusterDemo {
    public static void main(String[] args) {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 7001));
        nodes.add(new HostAndPort("127.0.0.1", 7002));
        nodes.add(new HostAndPort("127.0.0.1", 7003));
        nodes.add(new HostAndPort("127.0.0.1", 7004));
        nodes.add(new HostAndPort("127.0.0.1", 7005));
        nodes.add(new HostAndPort("127.0.0.1", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        String key = "name";
        System.out.println(jedisCluster.get(User.getKeyName() + "201641412230"));
        System.out.println(jedisCluster.get(key + 2));

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++)
            jedisCluster.set(key + i, "cc-djy");
        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("rediscluster执行时间：" + excTime + "s");

        Jedis jedis = JedisPoolUtil.getJedis();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++)
            jedis.set(key + i, "cc-djy");
        endTime = System.currentTimeMillis();
        excTime = (float) (endTime - startTime) / 1000;
        System.out.println("单个redis执行时间：" + excTime + "s");
    }

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDemoImpl userService1 = ctx.getBean(UserDemoImpl.class);
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("rediscluster执行时间：" + excTime + "s");
        System.out.println(userService1.findUserByKey(User.getKeyName()+"20164141223"));
    }
}
