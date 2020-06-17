package com.qgh.spring_mvc.moduels.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/2013:44
 */
public class Test {
    public static void main(String[] args) {


           /* List<Student> studentList = new ArrayList<Student>(){
                {
                    add(new Student("stu1",100.0));
                    add(new Student("stu2",97.0));
                    add(new Student("stu3",96.0));
                    add(new Student("stu4",95.0));
                }
            };
            Collections.sort(studentList, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Double.compare(o2.getScore(),o1.getScore());
                }
            });
            System.out.println(studentList);*/

        testOnePar(x->{});

    }
    public static void testOnePar(MyFunctionalInterface myFunctionalInterface){
        myFunctionalInterface.single("msg");
    }
}
