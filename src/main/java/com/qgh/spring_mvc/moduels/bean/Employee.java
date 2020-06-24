package com.qgh.spring_mvc.moduels.bean;

import com.qgh.spring_mvc.moduels.enums.SexEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 秦光泓
 * @title:雇员父类表
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:28
 */
@Data
public class Employee {
    private Long id;
    private String realName;
    private SexEnum sex = null;
    private Date birthday;
    private String mobile;
    private String email;
    private String position;
    private String note;
    //工牌按一对一级联
    private WorkCard workCard;
    // 雇员任务，一对多级联
    private List<EmployeeTask> emplyeeTaskList = null;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", note='" + note + '\'' +
                ", workCard=" + workCard +
                ", emplyeeTaskList=" + emplyeeTaskList +
                '}';
    }
}
