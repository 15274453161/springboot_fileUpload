package com.qgh.spring_mvc.util;

import javax.annotation.Resource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 秦光泓
 * @title:编写注解类，用于标记Excel导出需要的信息。使用下面的注解在实体的字段上，可以指定导出的时候，Excel表头的列名
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/4/311:02
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAttribute {
    /***
    * Excel 中的列名
    * @return
    * @date 2020/4/3  11:08
    */
    public abstract  String name();
    /***
    *列名对应的A、B、C、D不指定按照默认顺序排序
    * @return
    * @date 2020/4/3  11:09
    */
    public abstract String column() default "";

    /***
    *提示信息
    * @return
    * @date 2020/4/3  11:11
    */
    public abstract String prompt() default "";

    /***
    *设置只能选择不能输入的内容
    * @return
    * @date 2020/4/3  11:11
    */
    public abstract String[] combo() default {};

    /***
    *是否导出数据
    * @return
    * @date 2020/4/3  11:12
    */
    public abstract boolean isExport() default true;

    /**
     * 是否为重要字段（整列标红,着重显示）
     *
     * @return
     */
    public abstract boolean isMark() default false;

    /**
     * 是否合计当前列
     *
     * @return
     */
    public abstract boolean isSum() default false;

}
