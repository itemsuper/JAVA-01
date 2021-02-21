package com.geekbang.spring.course10.autoConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Resource;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class School implements ISchool {

//    @Autowired(required = true)
    @Resource(name = "klass")
    Klass class1;

    @Resource(name = "student100")
    Student student;

    @Override
    public void ding(){
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student);
    }

}
