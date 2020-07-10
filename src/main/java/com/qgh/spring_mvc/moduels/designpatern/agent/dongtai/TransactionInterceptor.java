package com.qgh.spring_mvc.moduels.designpatern.agent.dongtai;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2911:06
 */
public class TransactionInterceptor implements Interceptor {
    @Override
    public void intercept() {
        System.out.println("后置通知");
    }
    @Override
    public Object intercept(Invocation invocation) throws Exception{
        System.out.println("------插入前置通知代码-------------");
        Object result = invocation.process();//调用拦截的方法
        System.out.println("------插入后置处理代码-------------");
        return result;
    }
    /**生成目标类的代理对象*/
    @Override
    public Object plugin(Object target) {
        return HWInvocationHandler2.wrap(target,this);
    }
}
