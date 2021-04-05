package com.example.redisStudy.homework5;

import com.example.redisStudy.config.MyRedissonConfig;
import com.example.redisStudy.util.JedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
public class TestOrder {
    private static JedisPool jedisPool;
    private static MyRedissonConfig myJedisConfig = new MyRedissonConfig();

    @ResponseBody
    @GetMapping("/testOrder")
    public void testOrder(){
        jedisPool = myJedisConfig.redisPoolFactory();
        RedisPublisher redisPublisher = new RedisPublisher(jedisPool);
        redisPublisher.publishMsg("api_channel", "有新的订单需要处理...");
    }
}
