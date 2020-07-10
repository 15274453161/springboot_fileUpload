package com.qgh.spring_mvc.moduels.designpatern.agent.dongtai;

import java.lang.reflect.Method;

/**
 * @author 秦光泓
上面的动态代理确实可以把代理类中的业务逻辑抽离出来，但是我们注意到，只有前置代理，无法做到前后代理，所以还需要在优化下。
所以需要做更一步的抽象，把拦截对象信息进行封装，作为拦截器拦截方法的参数，把拦截目标对象真正的执行方法放到Interceptor中完成，
这样就可以实现前后拦截，并且还能对拦截
对象的参数等做修改。设计一个Invocation 对象。
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2911:15
 */

/**Invocation 类就是被代理对象的封装，也就是要拦截的真正对象。HWInvocationHandler修改如下：*/
public class Invocation {
    /**
     * 目标对象
     */
    private Object target;
    /**
     * 执行的方法
     */
    private Method method;
    /**
     * 方法的参数
     */
    private Object[] args;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    /**
     * 执行目标对象的方法
     */
    public Object process() throws Exception{
        return method.invoke(target,args);
    }
}
