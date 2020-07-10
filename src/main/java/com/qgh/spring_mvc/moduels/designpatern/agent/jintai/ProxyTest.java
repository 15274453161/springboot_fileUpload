package com.qgh.spring_mvc.moduels.designpatern.agent.jintai;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2814:01
 */
public class ProxyTest {
    public static void main(String[] args) {
        //被代理的学生张三，他的班费上交有代理对象monitor（班长）完成
        Teacher zhangsan = new Student("张三");

        //生成代理对象，并将张三传给代理对象
        Teacher monitor = new StudentsProxy(zhangsan);

        //班长代理上交班费
        monitor.giveMoney();
    }
}
