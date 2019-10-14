package com.cc.controller;

import com.cc.bean.User;
import com.cc.service.IUserService;
import com.cc.util.JedisPoolUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class redisDemo {
    /**
     * java通过jedis客户端操作redis服务器
     * redis作用：减去数据库访问压力
     * 如果redis的key存在，就从redis上读取，不存在再查询数据库
     */
    public static void main(String[] args) {
        String key = "class";
        Jedis jedis = JedisPoolUtil.getJedis();

//        string类型操作
        if (jedis.exists(key)) {
            System.out.println("1.从redis数据库中查询得到的：" + jedis.get(key));
        } else {
            String result = "16软卓2班";
            jedis.set(key, result);
            System.out.println("1.从MySQL数据库中查询得到：" + result);
        }

//        hash类型
        int id = 3;

        if (jedis.exists("users" + id)) {
            Map map = jedis.hgetAll("users" + id);
            System.out.println("2.从redis数据库中查询得到的：" + map.get("id") + "\t" + map.get("name") + "\t" + map.get("age"));
        } else {
            Map map = new HashMap();
            map.put("id", "201341412230");
            map.put("name", "cd");
            map.put("age", "22");
            jedis.hmset("users" + id, map);
            System.out.println("2.从MySQL数据库中查询得到：" + map);
        }
//
//        List<User> userList = new ArrayList<>();
//        long startTime = System.currentTimeMillis();
////        bean对象操作
//        for (int userId = 0; userId < 1000000; userId++) {
//            String userKey = User.getKeyName() + userId;
//            if (jedis.exists(userKey)) {
//                Map map = jedis.hgetAll(userKey);
//                User user = new User();
//                user.setId((String) map.get("id"));
//                user.setUsername((String) map.get("name"));
//                user.setAge((String) map.get("age"));
//                user.setSex((String) map.get("sex"));
//                userList.add(user);
//            } else {
//                User user = new User();
////            这里相当于从数据库获取数据保存到redis
//                user.setId("201641412230");
//                user.setUsername("cc");
//                user.setAge("22");
//                user.setSex("男");
//                Map map = new HashMap();
//                map.put("id", user.getId());
//                map.put("name", user.getUsername());
//                map.put("age", user.getAge());
//                map.put("sex", user.getSex());
//                jedis.hmset(userKey, map);
//                userList.add(user);
//            }
//        }
//        long endTime = System.currentTimeMillis();
//        float excTime = (float) (endTime - startTime) / 1000;
//        System.out.println("用户数量：" + userList.size());
//        System.out.println("执行时间：" + excTime + "s");
        JedisPoolUtil.closeJedis(jedis);
    }


}
