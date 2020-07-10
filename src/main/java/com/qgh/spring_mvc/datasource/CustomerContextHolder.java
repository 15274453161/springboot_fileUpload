package com.qgh.spring_mvc.datasource;

/**
 * @author 秦光泓
 * @title:这个主要负责设置上下文环境和获得上下文环境
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/913:51
 */
public class CustomerContextHolder {
    /**基础数据库*/
    public static final String FIRST_DATASOURCE = "firstAopDataSource";
    /**业务数据库*/
    public static final String SECOND_DATASOURCE = "secondAopDataSource";

    private static final ThreadLocal contextHolder = new ThreadLocal();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return (String) contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
