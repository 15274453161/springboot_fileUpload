package com.qgh.spring_mvc.moduels.designpatern.command;

/**
 * @author 秦光泓
 * @title:命令的调用则
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2813:26
 */
public class Invoker {
    private  Command command;

    public void setCommand(Command command) {
        this.command = command;
    }
    //执行命令
    public void action(){
        command.execute();
    }
}
