package com.qgh.spring_mvc.common.util;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Create By dongxiaoyong on /2019/9/2
 * description: 通过该类即可在普通工具类里获取spring管理的bean
 */
@Component
public class SpringTool implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private static final Logger logger = Logger.getLogger(SpringTool.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("applicationContext setter");
        SpringTool.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    /***
    * 根据类名小写获取Spring管理的bean
     * @param name
    * @return java.lang.Object
    * @date 2020/6/30  11:08
    */

    public Object getName(String name){
        return applicationContext.getBean(name);
    }
    /***
    * 根据类名获取Spring管理的bean
     * @param clazz
    * @return T
    * @date 2020/6/30  11:18
    */

    public <T> T getName(Class<?> clazz){
      return (T) applicationContext.getBean(clazz);
    }
    /***
    * 判断Spring中是否存在此bean
     * @param name
    * @return boolean
    * @date 2020/6/30  11:20
    */

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }
   /***
   * 判断是否是单例
    * @param name
   * @return boolean
   * @date 2020/6/30  11:20
   */

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }
}
