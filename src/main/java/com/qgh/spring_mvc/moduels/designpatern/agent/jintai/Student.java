package com.qgh.spring_mvc.moduels.designpatern.agent.jintai;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2813:53
 */
public class Student implements Teacher {
    private String name;
    public Student(String name) {
        this.name = name;
    }

    @Override
    public void giveMoney() {
        System.out.println(name + "上交班费100元");
    }
}
