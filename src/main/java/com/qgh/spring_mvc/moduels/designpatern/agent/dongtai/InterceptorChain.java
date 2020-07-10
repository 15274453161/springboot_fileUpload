package com.qgh.spring_mvc.moduels.designpatern.agent.dongtai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 秦光泓
 * @title:责任链设计模式
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2913:23
 */
public class InterceptorChain {
    private List<Interceptor> interceptorList = new ArrayList<>();

    /**
     * 插入所有拦截器
     */
    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptorList) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptorList.add(interceptor);
    }
    /**
     * 返回一个不可修改集合，只能通过addInterceptor方法添加
     * 这样控制权就在自己手里
     */
    public List<Interceptor> getInterceptorList() {
        return Collections.unmodifiableList(interceptorList);
    }
}
