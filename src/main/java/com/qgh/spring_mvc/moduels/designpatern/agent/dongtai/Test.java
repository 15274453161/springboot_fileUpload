package com.qgh.spring_mvc.moduels.designpatern.agent.dongtai;

import com.qgh.spring_mvc.moduels.proxy.jdk.TestDao;
import com.qgh.spring_mvc.moduels.proxy.jdk.TestDaoImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2911:08
 */
public class Test {
    /* public static void main(String[] args) {
         List<Interceptor> interceptorList = new ArrayList<>();
         interceptorList.add(new LogInterceptor());
         interceptorList.add(new TransactionInterceptor());

         TestDao target = new TestDaoImpl();
         TestDao targetProxy = (TestDao) HWInvocationHandler.wrap(target,interceptorList);
         targetProxy.modify();
     }*/
//优化
   /* public static void main(String[] args) {
        TestDao target = new TestDaoImpl();
        Interceptor transactionInterceptor = new TransactionInterceptor();
        TestDao targetProxy = (TestDao) HWInvocationHandler2.wrap(target,transactionInterceptor);
        targetProxy.modify();
    }*/
    //再优化
    /*public static void main(String[] args) {
        TestDao target = new TestDaoImpl();
        Interceptor transactionInterceptor = new TransactionInterceptor();
        //把事务拦截器插入到目标类中
        target = (TestDao) transactionInterceptor.plugin(target);
        target.modify();
    }*/

    //多个拦截器
   /* public static void main(String[] args) {
        TestDao target = new TestDaoImpl();
        Interceptor transactionInterceptor = new TransactionInterceptor();
        //把事务拦截器插入到目标类中
        target = (TestDao) transactionInterceptor.plugin(target);

        LogInterceptor logInterceptor = new LogInterceptor();
        target= (TestDao) logInterceptor.plugin(target);

        target.modify();
    }*/

    public static void main(String[] args) {
        TestDao target = new TestDaoImpl();
        Interceptor transactionInterceptor = new TransactionInterceptor();
        LogInterceptor logInterceptor = new LogInterceptor();
        InterceptorChain interceptorChain = new InterceptorChain();
        interceptorChain.addInterceptor(transactionInterceptor);
        interceptorChain.addInterceptor(logInterceptor);
        target = (TestDao) interceptorChain.pluginAll(target);
        target.modify();
    }
}
