package package com.geekbang.spring.course9.test;

import com.geekbang.spring.course9.BeanFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJavaAnnotation {
    @Test
    public void test(){
        ApplicationContext ctx= new ClassPathXmlApplicationContext("applicationContext.xml");
        BeanFactory beanFactory=(BeanFactory) ctx.getBean("beanJavaAnnotation");
        beanFactory.Beantest();
    }
}
