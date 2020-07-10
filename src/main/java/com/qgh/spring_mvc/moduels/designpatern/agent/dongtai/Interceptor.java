package com.qgh.spring_mvc.moduels.designpatern.agent.dongtai;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2911:05
 */

public interface Interceptor {
    /**
     * 具体拦截处理
     */
    void intercept();

    /**
     * 优化后的拦截方法
     */
    Object intercept(Invocation invocation) throws Exception;
/**上面这样就能实现前后拦截，并且拦截器能获取拦截对象信息。但是测试代码的这样调用看着很别扭，
 * 对应目标类来说，只需要了解对他插入了什么拦截就好。
 再修改一下，在拦截器增加一个插入目标类的方法。*/
    /**
     * 插入目标类
     */
    Object plugin(Object target);

}
