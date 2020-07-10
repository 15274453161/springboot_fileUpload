package com.qgh.spring_mvc.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 秦光泓
 * @title:
 * 这个类实现了determineCurrentLookupKey方法，该方法返回一个Object，
 * 一般是返回字符串，也可以是枚举类型。该方法中直接使用了CustomerContextHolder.getCustomerType()
 * 方法获得上下文环境并直接返回。
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/914:12
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /***
    * 获取使用的数据源
    * @return java.lang.Object
    * @date 2020/7/9  14:15
    */
    @Override
    protected Object determineCurrentLookupKey() {
        return CustomerContextHolder.getCustomerType();
    }
}
