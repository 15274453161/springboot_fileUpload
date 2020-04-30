package com.qgh.spring_mvc.common.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/4/2415:29
 */
@Component
//配置文件中的拦截器的前缀
@ConfigurationProperties(prefix = "unitary.authority")//这个必须和上面这个注解一起使用
public class UnitaryAuthorityBean {
    /**
     * 是否开启拦截
     */
    private boolean authc;
    /**
     * 不拦截的url 是个数组
     */
    private String[] anonUrlArray;
    /**
     * 支撑平台权限校验url
     */
    private String unitaryAuthorityUrl;

    public boolean isAuthc() {
        return authc;
    }

    public void setAuthc(boolean authc) {
        this.authc = authc;
    }

    public String[] getAnonUrlArray() {
        return anonUrlArray;
    }

    public void setAnonUrlArray(String[] anonUrlArray) {
        this.anonUrlArray = anonUrlArray;
    }

    public String getUnitaryAuthorityUrl() {
        return unitaryAuthorityUrl;
    }

    public void setUnitaryAuthorityUrl(String unitaryAuthorityUrl) {
        this.unitaryAuthorityUrl = unitaryAuthorityUrl;
    }
}
