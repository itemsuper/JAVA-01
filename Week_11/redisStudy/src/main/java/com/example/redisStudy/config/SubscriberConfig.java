package com.example.redisStudy.config;

import com.example.redisStudy.homework5.BusinessSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 ** 消息订阅业务处理配置类
 **/
@Configuration
public class SubscriberConfig {

    @Autowired
    private BusinessSubService businessSubService;

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        List<PatternTopic> topicList = new ArrayList<>();
        // api_channel 是对应的频道名称
        topicList.add(new PatternTopic("api_channel"));
        container.addMessageListener(listenerAdapter, topicList);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter() {
        // receiveMessage 是 BusinessSubService 类中的接收信息的方法（名称需要对应起来）
        return new MessageListenerAdapter(businessSubService, "receiveMessage");
    }
}
