package com.qgh.spring_mvc.moduels.thread;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/1317:39
 */
public class MyThread extends Thread{
    //在类中指定线程对象要执行的功能代码
    //重写run方法
    @Override
    public void run() {

            System.out.println(this.getName());

    }
}
