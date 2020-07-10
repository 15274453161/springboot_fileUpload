package com.qgh.spring_mvc.moduels.designpatern.agent.dongtai;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2911:06
 */
public class HWInvocationHandler implements InvocationHandler {
    private Object target;

    private List<Interceptor> interceptorList = new ArrayList<>();

    public HWInvocationHandler(Object target,List<Interceptor> interceptorList) {
        this.target = target;
        this.interceptorList = interceptorList;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //处理多个拦截器
        for (Interceptor interceptor : interceptorList) {
            interceptor.intercept();
        }
        return method.invoke(target, args);
    }
   /***
   * 生成目标类的代理对象
    * @param target
    * @param interceptorList
   * @return java.lang.Object
   * @date 2020/6/29  11:08
   */

    public static Object wrap(Object target,List<Interceptor> interceptorList) {
        HWInvocationHandler targetProxy = new HWInvocationHandler(target, interceptorList);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),targetProxy);
    }
}
