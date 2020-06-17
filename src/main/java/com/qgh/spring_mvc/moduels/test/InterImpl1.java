package com.qgh.spring_mvc.moduels.test;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/1317:56
 */
public class InterImpl1  implements Inter,Runnable {
    @Override
    public void produce() throws InterruptedException {
        System.out.println("实现类1");
    }

    @Override
    public void run() {
        System.out.println("InterImpl");
    }
}
