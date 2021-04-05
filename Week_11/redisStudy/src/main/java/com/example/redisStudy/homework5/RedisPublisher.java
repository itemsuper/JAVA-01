package com.example.redisStudy.homework5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisPublisher {
    private final static Logger logger = LoggerFactory.getLogger(RedisPublisher.class);

    private final JedisPool jedisPool;

    public RedisPublisher(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 发布一个消息
     *
     * @param channel
     * @param message
     */
    public void publishMsg(String channel, String message) {
        Jedis jedis = null;
        System.out.println("发布消息频道：" + channel + ";内容：" + message);
        try {
            jedis = jedisPool.getResource();
            jedis.publish(channel, message);
        } catch (Exception e) {
            System.out.println("第一次publish发布信息失败,channel:" + channel + "失败原因:"+e);
            // 再次尝试
            try {
                if (jedis == null) {
                    jedis = jedisPool.getResource();
                }
                jedis.publish(channel, message);
            } catch (Exception e1) {
                System.out.println( "再次publish信息失败,channel:" + channel + "失败原因:"+e1);
            } finally {
                if (jedis != null) {
                    try {
                        jedis.close();
                    } catch (Exception e2) {
                        jedis = null;
                    }
                }
            }
        }
    }
}
