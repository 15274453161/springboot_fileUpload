package com.qgh.spring_mvc.moduels.designpatern.command;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2813:23
 */
public abstract class Command {
    protected final Receiver receiver;

    protected Command(Receiver receiver) {
        this.receiver = receiver;
    }

    abstract void execute();
}
