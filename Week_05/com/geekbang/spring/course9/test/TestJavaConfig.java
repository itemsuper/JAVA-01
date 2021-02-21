package package com.geekbang.spring.course9.test;

import com.geekbang.spring.course9.BeanFactory;
import com.geekbang.spring.course9.javaConfig.BeanConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestJavaConfig {
    @Test
    public void test(){
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(BeanConfig.class);
        BeanFactory beanFactorys=applicationContext.getBean(BeanFactory.class);
        beanFactorys.Beantest();
    }
}
