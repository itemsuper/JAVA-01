package com.example.redisStudy.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class MyRedissonConfig {

    //todo 获取不到yml文件的参数

    @Value("${spring.redis.host}")
    private static String host;

    @Value("${spring.redis.port}")
    private static int port;

    @Value("${spring.redis.jedis.pool.max-active}")
    private static int maxTotal;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private static int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private static int minIdle;

    @Value("${spring.redis.password}")
    private static String password;

    @Value("${spring.redis.timeout}")
    private static int timeout;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(){
        // 创建配置  记得加redis://
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        // 根据配置创建RedissClient客户端
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }


    @Bean
    public static JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(1000);
        jedisPoolConfig.setMaxWaitMillis(10*1000);
        jedisPoolConfig.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 1000, null);
        System.out.println("JedisPool注入成功！！");
        return jedisPool;
    }

}
