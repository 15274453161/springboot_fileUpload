package com.qgh.spring_mvc.moduels.proxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 秦光泓
 * @title:cglib的代理类
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2410:41
 */
public class CglibDynamicProxy implements MethodInterceptor {
    /**
     * 被代理的对象
     */
    private Object target;

    /***
     * 创建代理对象 生成CGLIB的代理对象
     * @param target 目标对象 需要
     * @return java.lang.Object
     * @date 2020/6/24  10:43
     */

    public Object createProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        /**确定需要增强的类 设置其父类*/
        enhancer.setSuperclass(target.getClass());
        /**确定代理逻辑对象为当前的对象，要求当前的对象实现MethodInyerceptor方法*/
        enhancer.setCallback(this);
        /**返回创建的代理对象*/
        return enhancer.create();
    }

    /***
     * interceptor方法会在执行目标方法是调用
     * @param o 根据指定父类生成的代理对象
     * @param method 拦截的方法
     * @param objects 方法的参数
     * @param methodProxy 方法的代理对象 用于执行父类的方法
     * @return java.lang.Object
     * @date 2020/6/24  10:51
     */

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        /**目标方法执行返回代理结果*/
        Object result = methodProxy.invokeSuper(o, objects);
        return result;
    }
}
