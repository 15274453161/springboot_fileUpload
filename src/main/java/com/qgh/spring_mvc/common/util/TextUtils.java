package com.qgh.spring_mvc.common.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * created by zhuwenquan on 2019/11/1
 * 文本工具
 */
public class TextUtils {
    /**
     * 手机号码处理
     * @param phone 原始string
     * @param stringBuffer 返回信息
     * @return true:返回处理结果；false:返回错误原因
     */
    public static boolean toPhoneNb(String phone, StringBuffer stringBuffer ){
        try {
            stringBuffer.setLength(0);
            if (StringUtils.isBlank(phone)) {
                stringBuffer.append("手机号码不能为空！");
                return false;
            }
            phone = phone.replaceAll(" ", "");
            BigDecimal phoneBd = new BigDecimal(phone);
            phone = phoneBd.toPlainString();
            phone = phone.replaceAll("\\.00", "");
            if (!PhoneUtils.isPhone(phone)) {
                stringBuffer.append("手机号码不正确！");
                return false;
            }else {
                stringBuffer.append( phone );
                return true;
            }
        } catch (Exception e) {
            stringBuffer.append("手机号码不正确！" + e.getMessage());
            return false;
        }
    }
}
