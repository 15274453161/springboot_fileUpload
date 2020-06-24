package com.qgh.spring_mvc.moduels.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 秦光泓
 * @title:基于JDK的动态代理
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2410:06
 */
public class JDKDynamicProxy implements InvocationHandler {
    /**声明目标类接口对象——>真实的服务对象*/
    private Object target;

    /**创建代理的方法 创建代理对象和真实对象的代理关系 并返回代理对象*/

    public Object createProxy(Object target){
        this.target = target;
        /**JDK代理需要提供接口*/
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    /***
    * 代理的逻辑方法，所有动态代理类的方法都交给该方法处理
     * @param proxy 代理对象
     * @param method 被调用的方法
     * @param args  执行方法时需要的参数
    * @return java.lang.Object
    * @date 2020/6/24  10:12
    */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        method.invoke(target,args);
        return result;
    }
}
