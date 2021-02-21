package com.geekbang.spring.course10.autoConfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {

    @Bean
    public Student student() {
        Student student = new Student();
        student.create();
        return student;
    }

    @Bean
    @ConditionalOnBean(name="student100")
    public Klass klass(Student student){

        Student student1 = new Student();
        student1.setId(102);
        student1.setName("item");

        List<Student> list = new ArrayList<Student>();
        list.add(student);
        list.add(student1);
        Klass klass = new Klass();
        klass.setStudents(list);

        return klass;
    }

    /**
     * 这里加了ConditionalOnBean注解，就代表如果klass存在才实例化school
     */
    @Bean
    @ConditionalOnBean(name = "klass")
    public School school(Klass klass) {
        System.out.println("--------------access school----------------"+klass);
        //这里如果klass实体没有成功注入 这里就会报空指针
        School school = new School();
        school.setClass1(klass);

        System.out.println("-----------------access school2----------------"+school);
        return school;
    }
}
