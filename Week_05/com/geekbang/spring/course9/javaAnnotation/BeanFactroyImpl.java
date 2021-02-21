package com.geekbang.spring.course9.javaAnnotation;

import com.geekbang.spring.course9.BeanFactory;
import org.springframework.stereotype.Service;

@Service("beanJavaAnnotation")
public class BeanFactroyImpl implements BeanFactory {
    @Override
    public void Beantest() {
        System.out.println("----------------基于Java注解实现装配Bean!-------------------");
    }
}
