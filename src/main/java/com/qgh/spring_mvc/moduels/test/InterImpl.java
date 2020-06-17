package com.qgh.spring_mvc.moduels.test;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/1317:56
 */
public class InterImpl extends AbstacrPro implements Inter {
    @Override
    public void produce() throws InterruptedException {
        System.out.println("实现类");
    }
}
