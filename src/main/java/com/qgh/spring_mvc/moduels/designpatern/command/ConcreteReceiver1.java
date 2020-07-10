package com.qgh.spring_mvc.moduels.designpatern.command;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2813:35
 */
public class ConcreteReceiver1 extends Receiver {
    @Override
    void doSomething() {
        System.out.println("执行任务");
    }
}
