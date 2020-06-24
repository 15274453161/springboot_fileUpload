package com.qgh.spring_mvc.moduels.proxy.jdk;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2410:21
 */
public class TestMain {
    public static void main(String[] args) {
        TestDao subject=new TestDaoImpl();
        /**创建代理对象*/
        JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy();
        /**通过代理对象调用创建代理的方法 获取代理后的对象*/
        TestDao testDaoProxy = (TestDao) jdkDynamicProxy.createProxy(subject);
       // testDaoProxy.save();
       // testDaoProxy.modify();


    }
}
