package com.geekbang.spring.course10.autoConfig;

import lombok.Data;
import java.util.List;

@Data
public class Klass {

    List<Student> students;

    public void dong(){
        System.out.println(this.getStudents());
    }

}
