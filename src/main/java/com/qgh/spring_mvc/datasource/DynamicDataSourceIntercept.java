package com.qgh.spring_mvc.datasource;

import org.apache.commons.lang.ClassUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @author 秦光泓
 * @title:数据源的切面
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/914:16
 */
@Component
@Aspect
public class DynamicDataSourceIntercept {
    public static final Logger logger = Logger.getLogger(DynamicDataSourceIntercept.class);
    /***
    * 定义切入点-->需要织入逻辑的点
    * @return void
    * @date 2020/7/9  17:09
    */
    /**                  返回值    权限路径                        所有类   方法参数*/
    @Pointcut("execution(* com.qgh.spring_mvc.moduels.service.impl..*(..))")
    public void pointCut(){

    }
     /***
     * 方法执行前的 织入
      * @param joinPoint
     * @return void
     * @date 2020/7/9  17:15
     */

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        logger.info("================数据源切换开始处理=================");
        /**代理对象获取 ---->拿到需要织入的类*/
        Class<?> clazz = joinPoint.getTarget().getClass();
        String className = clazz.getName();
        if (ClassUtils.isAssignable(clazz, Proxy.class)) {
            className = joinPoint.getSignature().getDeclaringTypeName();
        }
        String methodName = joinPoint.getSignature().getName();

        logger.info("类名：" + className + " , 方法名：" + methodName);
        /**如果该方法的里面包含以下字符 就切换到相应的数据源*/
        if (methodName.toLowerCase().contains("4sisp")) {
            CustomerContextHolder
                    .setCustomerType(CustomerContextHolder.FIRST_DATASOURCE);
        } else if (methodName.toLowerCase().contains("4cssp")) {
            logger.info("切换数据源4cssp");
            CustomerContextHolder
                    .setCustomerType(CustomerContextHolder.SECOND_DATASOURCE);
        }else {
            CustomerContextHolder.clearCustomerType();
        }
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        logger.error("异常类=======:" + joinPoint.getTarget().getClass() + "=="
                + e.getMessage(), e);
    }
}
