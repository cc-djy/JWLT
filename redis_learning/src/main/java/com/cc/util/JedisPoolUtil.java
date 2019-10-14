package com.cc.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class JedisPoolUtil {
    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(100000);
        //最大空闲数
        jedisPoolConfig.setMaxIdle(1);
        String host = "10.60.2.132";
        int port = 6379;
        //jedis连接池
        jedisPool = new JedisPool(jedisPoolConfig, host, port);
    }

    public static synchronized Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("123456");
        return jedis;
    }

    public static synchronized void closeJedis(Jedis jedis) {
        jedis.close();
    }
}
