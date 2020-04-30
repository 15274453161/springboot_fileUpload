package com.qgh.spring_mvc.common.util;

/**
 * @author 秦光泓
 * @title:常量的配置
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/4/2111:03
 */
public class Constants {
    public static String RESULT_KEY_STATUSCODE = "statusCode";
    public static String RESULT_KEY_MESSAGE = "message";
    public static String RESULT_MESSAGE_SUCCESS = "200";
    public static String RESULT_MESSAGE_ERROR = "0";

    /****************** 渠道编码 ******************/
    public final static String CHANNELCODE = "channelCode";
    /**
     * 小程序
     */
    public static final String CHANNELCODE_MINIPROGRAM = "MiniProgram";
    /**
     * 银行报文编码格式
     */
    public static final String BANK_ENCODING_TYPE = Interface.getInstance().get("bankEncodingType");

    /**
     * 银行报文文件编码格式
     */
    public static final String BANK_FILE_ENCODING_TYPE = Interface.getInstance().get("bankFileEncodingType");
}
