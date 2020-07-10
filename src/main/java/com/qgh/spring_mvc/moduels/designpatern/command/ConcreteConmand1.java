package com.qgh.spring_mvc.moduels.designpatern.command;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2813:24
 */
public class ConcreteConmand1 extends Command {
    /**设置默认的执行者*/
    protected ConcreteConmand1() {
        super(new ConcreteReceiver1());
    }
    /**设置的执行者*/
    protected ConcreteConmand1(Receiver receiver) {
        super(receiver);
    }
    @Override
    void execute() {
        super.receiver.doSomething();
    }
}
