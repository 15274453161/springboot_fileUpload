package com.qgh.spring_mvc.moduels.designpatern.agent.dongtai;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2911:05
 */
public class LogInterceptor implements Interceptor {
    @Override
    public void intercept() {
        System.out.println("------插入前置通知代码-------------");
    }

    @Override
    public Object intercept(Invocation invocation) throws Exception {
        System.out.println("登录前置");
       Object object = invocation.process();
        return object;
    }

    @Override
    public Object plugin(Object target) {
        return HWInvocationHandler2.wrap(target,this);
    }
}
