package com.qgh.spring_mvc.moduels.test;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/1317:57
 */
public abstract class AbstacrPro implements Inter,Runnable {
    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
