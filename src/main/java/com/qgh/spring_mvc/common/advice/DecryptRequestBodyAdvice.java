package com.qgh.spring_mvc.common.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qgh.spring_mvc.common.util.encryption.AESUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author 秦光泓
 * @title:针对请求参数解密 类似使用aop的方式
 * @projectName Integration
 * @description: TODO
 * @date 2020/7/1513:10
 */
@Component
@ControllerAdvice(basePackages = "com.qgh.spring_mvc.moduels.controller")//那些请求需要解密
@Slf4j
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    /**
     * 解密密钥
     */
    @Value("${system.encrykey}")
    private String encrykey;

    /**
     * 前端加密参数名 {"encryData:"XXXXX"} XXXX是实际json参数
     */
    @Value("${system.encryParamKey}")
    private String encryParamKey;

    /**
     * 判断是否支持,此处如果返回false , 则不执行当前Advice的业务
     * @Authoor :dongxiaoyong
     * @Date :2020/2/17 11:14
     * @param methodParameter :
     * @param targetType :
     * @param converterType :
     * @return : boolean
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        //return methodParameter.hasParameterAnnotation(Encrypt.class);//判断请求参数解密。必须使用@Encrypt注解才进行解密
        return true;
    }
    /**
     * 在请求体未读取(转换)时调用
     * 在此做些编码 / 解密 / 封装参数为对象的操作
     * @Authoor :dongxiaoyong
     * @Date :2020/2/15 18:51
     * @param inputMessage :
     * @param methodParameter :
     * @param targetType :
     * @param selectedConverterType :
     * @return : org.springframework.http.HttpInputMessage
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> selectedConverterType) throws IOException {
        HttpHeaders httpHeaders = inputMessage.getHeaders();
        if(null != httpHeaders){
            //判断请求头部是否包含 encryRequest:true；包含才进行参数解密
            List<String> header = httpHeaders.get("encryRequest");
            if(CollectionUtils.isNotEmpty(header)){
                for(String encry : header){
                    if("true".equals(encry)){
                        try {
                            return new HttpInputMessage() {
                                @Override
                                public InputStream getBody() throws IOException {
                                    String bodyStr = IOUtils.toString(inputMessage.getBody(),"utf-8");
                                    /**
                                     * {
                                     * 	"encryData":"bqECc4IssWb7CSVmrDx6oA+I6AR3vQ06J2XkDYI2spzETs3r7c8lUAjuu02tfHfR1gS8gp8zo3L/NzrfFZfuBMJpzXay9+6iSuuDNfDL064="
                                     * }
                                     */
                                    log.debug("接收到原始请求数据={}", bodyStr);
                                    try {
                                        JSONObject jsonObject = JSON.parseObject(bodyStr);
                                        if (null != jsonObject ){
                                            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                                                //要解密的参数
                                                try{
                                                    if(encryParamKey.equals(entry.getKey())){
                                                        entry.setValue(AESUtil.AESDncode(encrykey,entry.getValue().toString()));
                                                        jsonObject.put(entry.getKey(),entry.getValue());
                                                        System.out.println(entry.getValue());
                                                    }
                                                }catch (Exception e){
                                                    log.error("解密请求参数异常！", e);
                                                    throw new RuntimeException("解密请求参数异常！");
                                                }
                                            }
                                           bodyStr = JSONObject.toJSONString(jsonObject.getJSONObject(encryParamKey));
                                          //  bodyStr = jsonObject.getString(encryParamKey);
                                          //  bodyStr = jsonObject.toJSONString();
                                           // bodyStr = jsonObject.toString();
                                            /**
                                             * {"pageno":1,"pagesize":10,"name":"董晓勇","roleIdList":[477,476]}
                                             */
                                            log.debug("解密后数据={}",bodyStr);
                                        }
                                    } catch (Exception e) {
                                        log.error("解密请求参数异常！", e);
                                        throw new RuntimeException("解密请求参数异常！");
                                    }
                                    return  IOUtils.toInputStream(bodyStr,"utf-8");
                                }

                                @Override
                                public HttpHeaders getHeaders() {
                                    return inputMessage.getHeaders();
                                }
                            };
                        }catch (Exception e){
                            log.error("解密请求参数异常！",  e);
                            throw new RuntimeException("解密请求参数异常！");
                        }
                    }
                }
            }
        }
        return inputMessage;
    }

    /**
     * 在请求体完成读取后调用
     * @Authoor :dongxiaoyong
     * @Date :2020/2/15 18:51
     * @param body :
     * @param inputMessage :
     * @param parameter :
     * @param targetType :
     * @param converterType :
     * @return : java.lang.Object
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }


    /**
     * 当请求体为空时调用
     * @Authoor :dongxiaoyong
     * @Date :2020/2/15 18:52
     * @param var1 :
     * @param var2 :
     * @param var3 :
     * @param var4 :
     * @param var5 :
     * @return : java.lang.Object
     */
    @Override
    public Object handleEmptyBody(@Nullable Object var1, HttpInputMessage var2, MethodParameter var3, Type var4, Class<? extends HttpMessageConverter<?>> var5) {
        return var1;
    }


}







