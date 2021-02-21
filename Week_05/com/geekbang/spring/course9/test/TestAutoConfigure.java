package com.geekbang.spring.course9.test;

import com.geekbang.spring.beanDemo.course9.autoConfigure.BeanFactroyImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAutoConfigure {
    @Autowired
    private BeanFactroyImpl beanFactory;
    @Test
    public void test(){
        beanFactory.Beantest();
    }

}
