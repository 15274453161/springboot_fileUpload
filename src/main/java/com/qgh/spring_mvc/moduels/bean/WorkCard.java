package com.qgh.spring_mvc.moduels.bean;

import lombok.Data;

/**
 * @author 秦光泓
 * @title:工牌表
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:23
 */
@Data
public class WorkCard {
    private Long id;
    private Long empId;
    private String realName;
    private String department;
    private String mobile;
    private String position;
    private String note;

    @Override
    public String toString() {
        return "WorkCard{" +
                "id=" + id +
                ", empId=" + empId +
                ", realName='" + realName + '\'' +
                ", department='" + department + '\'' +
                ", mobile='" + mobile + '\'' +
                ", position='" + position + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
