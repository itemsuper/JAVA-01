package com.example.redisStudy.util;

import com.example.redisStudy.config.MyRedissonConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {

    private static JedisPool jedisPool;
    private static MyRedissonConfig myJedisConfig = new MyRedissonConfig();

    /**
     * 对某个键的值自减
     * @author liboyi
     * @param key 键
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static long setdecr(String key, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedisPool = myJedisConfig.redisPoolFactory();
            jedis = jedisPool.getResource();
            result =jedis.decr(key);
            if (result<=1 && cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            System.out.println("set "+ key + " = " + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("set "+ key + " = " + result);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

}
