package com.geekbang.spring.course9.javaConfig;

import com.geekbang.spring.course9.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public BeanFactory beanFactory(){
        return new BeanFactoryImpl();
    }
}
