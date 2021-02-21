package com.geekbang.spring.course9.javaConfig;

import com.geekbang.spring.course9.BeanFactory;

public class BeanFactoryImpl implements BeanFactory {
    @Override
    public void Beantest() {
        System.out.println("----------------基于Java Config实现bean的装配!-------------------");
    }
}
