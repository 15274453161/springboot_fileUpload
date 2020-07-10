package com.qgh.spring_mvc.moduels.designpatern.command;

/**
 * @author 秦光泓
 * @title:为什么 Receiver 是一个抽象类呢? 因为接收者可以有多个, 有多个就需要定义一个所有特性的抽象集合.
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2813:17
 */
/**命令的执行者*/
public abstract class Receiver {
    abstract void doSomething();
}
