package com.qgh.spring_mvc.moduels.designpatern.agent.jintai;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2813:54
 */
/***
*
 StudentsProxy类，这个类也实现了Teacher接口，
 由于实现了Teacher接口，同时持有一个学生对象，
 那么他可以代理学生类对象执行上交班费（执行giveMoney()方法）行为。
*/


public class StudentsProxy implements Teacher {
    //被代理的学生
    Student stu;

    public StudentsProxy(Teacher stu) {
        // 只代理学生对象
        if(stu.getClass() == Student.class) this.stu = (Student) stu;
    }

    //代理上交班费，调用被代理 学生的上交班费行为
    public void giveMoney() {
        System.out.println("张三家里是土豪，应该比其它人交更多的班费！");
        stu.giveMoney();
        System.out.println("张三班费交的最多，你就是班长了！");

    }
}
