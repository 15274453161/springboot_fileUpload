package com.qgh.spring_mvc.moduels.proxy.cglib;

/**
 * @author 秦光泓
 * @title:基于CGLIB的动态代理
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2410:40
 */
public class TestDao {
    void save(){
        System.out.println("save cglib");
    }
    void modify(){
        System.out.println("modify cglib");
    }
}
