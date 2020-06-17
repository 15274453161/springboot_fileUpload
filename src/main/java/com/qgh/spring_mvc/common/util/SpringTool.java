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
    private static Logger logger = Logger.getLogger(SpringTool.class);
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        logger.info("------SpringContextUtil setApplicationContext-------");
        SpringTool.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 注意 bean name默认 = 类名(首字母小写)
     * 例如: A8sClusterDao = getBean("a8sClusterDao")
     * @param name
     * @return
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 根据类名获取到bean
     * @param <T>
     * @param clazz
     * @return
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBeanByName(Class<T> clazz) throws BeansException {
        try {
            char[] cs=clazz.getSimpleName().toCharArray();
            //首字母大写到小写
            cs[0] += 32;
            return (T) applicationContext.getBean(String.valueOf(cs));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }
}
