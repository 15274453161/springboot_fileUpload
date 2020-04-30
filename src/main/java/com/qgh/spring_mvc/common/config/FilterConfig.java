package com.qgh.spring_mvc.common.config;

import com.qgh.spring_mvc.common.filter.UnitaryAuthorityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 秦光泓
 * @title:过滤器的配置类
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/4/2415:25
 */
@Configuration
public class FilterConfig {
    //自定义的过滤器
    @Autowired
    private UnitaryAuthorityFilter unitaryAuthorityFilter;
    @Bean
    public FilterRegistrationBean registerAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(unitaryAuthorityFilter);
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        registration.setOrder(1);  //值越小，Filter越靠前。
        return registration;
    }
}
