package com.qgh.spring_mvc.moduels.bean;

import com.qgh.spring_mvc.common.util.ExcelAttribute;
import lombok.Data;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/910:00
 */
@Data
public class AccountBean {
    @ExcelAttribute(name = "姓名",column = "B")
    private String accountName;//用户名
    @ExcelAttribute(name = "身份证",column = "C")
    private String SFZH;//身份证
    @ExcelAttribute(name = "手机号",column = "D")
    private String phone;//手机号
    @ExcelAttribute(name = "使用类型",column = "E")
    private String channelcode;//渠道编码 注册渠道类型
    @ExcelAttribute(name = "创建时间",column = "F")
    private String createTime;//创建时间
}
