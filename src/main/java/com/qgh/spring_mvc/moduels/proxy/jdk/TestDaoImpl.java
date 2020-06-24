package com.qgh.spring_mvc.moduels.proxy.jdk;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2410:03
 */
public class TestDaoImpl implements TestDao {
    @Override
    public void save() {
        System.out.println("save 的业务逻辑");
    }

    @Override
    public void modify() {
        System.out.println("modify 的业务逻辑");
    }
}
