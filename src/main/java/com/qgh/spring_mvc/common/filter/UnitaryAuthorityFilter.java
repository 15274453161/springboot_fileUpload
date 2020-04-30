package com.qgh.spring_mvc.common.filter;

import com.alibaba.fastjson.JSON;
import com.qgh.spring_mvc.common.controller.BaseController;
import com.qgh.spring_mvc.common.util.Constants;
import com.qgh.spring_mvc.common.util.HttpUtils;
import com.qgh.spring_mvc.common.vo.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 秦光泓
 * @title:统一权限过滤器
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/4/2415:27
 */
@Component
public class UnitaryAuthorityFilter implements Filter {
    @Autowired
    private  UnitaryAuthorityBean authorityBean;
    //请求的url和拦截的url进行匹配
    private final PathMatcher pathMatcher = new AntPathMatcher();
    private static Logger logger = LoggerFactory.getLogger(UnitaryAuthorityFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            //判断是否开启了拦截器

            if (!authorityBean.isAuthc()) {
                chain.doFilter(request, response);
                return;
            } else {//开启了拦截器 判断请求是否有权限访问
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                String currentURL = httpRequest.getServletPath();
                System.out.println(currentURL);
                // 过滤白名单  复核白名单里面的请求放行
                if (isWhiteURL(currentURL)) {
                    chain.doFilter(request, response);
                    return;
                }
                //
                Cookie[] cookies = httpRequest.getCookies();
                if (cookies != null) {
                    String ticket = "";
                    String accountType = "";
                    for (Cookie cookie : cookies) {
                        if ("ticket".equals(cookie.getName()) && StringUtils.isNotBlank(cookie.getValue())) {
                            ticket = cookie.getValue();
                        }
                        if ("accountType".equals(cookie.getName()) && StringUtils.isNotBlank(cookie.getValue())) {
                            accountType = cookie.getValue();
                        }
                        if(StringUtils.isNotBlank(ticket) && StringUtils.isNotBlank(accountType)){
                            /** 通过接口方式判断用户是否登录 **/
                            String resultJson = HttpUtils.doGet(authorityBean.getUnitaryAuthorityUrl().replace("{ticket}",ticket).replace("{accountType}",accountType));
                            if (StringUtils.isNotBlank(resultJson)){
                                logger.info("通过接口方式判断用户是否登录接口响应： " + resultJson);
                                Result result = JSON.parseObject(resultJson,Result.class);
                                if( null != result && Constants.RESULT_MESSAGE_SUCCESS.equals(result.getStatusCode())) {
                                    chain.doFilter(request, response);
                                    return;
                                }
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            logger.error("统一权限过滤器，调用支撑平台接口校验，拦截异常：",e);
            returnServerErrorValue(response);
            return;
        }
        returnNoAuthValue(response);
    }

    /**
     * 判断是否请求url是否在白名单中
     * @Author :dongxiaoyong
     * @Date :2020/3/10 10:19
     * @param currentURL : 当前请求url
     * @return : boolean
     */
    private boolean isWhiteURL(String currentURL) {
        for (String whiteURL : authorityBean.getAnonUrlArray()) {
            if (pathMatcher.match(whiteURL, currentURL)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回登录超时结果给前端，重新登录
     * @Author :dongxiaoyong
     * @Date :2020/3/9 16:28
     * @param response :{ "message": "请先登录！", "statusCode": "403" }
     * @return : void
     */
    private void returnNoAuthValue(ServletResponse response)
            throws IOException {
        Result result = new Result(BaseController.RESULT_MESSAGE_NOAUTH,BaseController.TEMPLATE_NOAUTH_MESSAGE,null);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 返回服务器结果给前端
     * @Author :dongxiaoyong
     * @Date :2020/3/9 16:28
     * @param response :{ "message": "服务器异常，请稍后再试！", "statusCode": "0" }
     * @return : void
     */
    private void returnServerErrorValue(ServletResponse response)
            throws IOException {
        Result result = new Result(BaseController.RESULT_MESSAGE_ERROR,BaseController.TEMPLATE_ERROR_MESSAGE,null);
        //设置编码的方式为utf-8
        response.setCharacterEncoding("UTF-8");
        //设置返回数据类型为json
        response.setContentType("application/json; charset=utf-8");
        //将json对象转换为json字符串
        response.getWriter().write(JSON.toJSONString(result));
    }
}
