package com.geekbang.spring.course9.xml;

import com.geekbang.spring.course9.BeanFactory;

public class BeanFactroyImpl implements BeanFactory {
    @Override
    public void Beantest() {
        System.out.println("----------------传统的XML实现装配bean!-------------------");
    }
}
