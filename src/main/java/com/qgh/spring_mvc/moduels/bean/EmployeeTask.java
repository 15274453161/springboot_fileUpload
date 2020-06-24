package com.qgh.spring_mvc.moduels.bean;

import lombok.Data;

/**
 * @author 秦光泓
 * @title:雇员任务表
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:26
 */
@Data
public class EmployeeTask {
    private Long id;
    private Long empId;
    private Task task = null;
    private String taskName;
    private String note;

    @Override
    public String toString() {
        return "EmployeeTask{" +
                "id=" + id +
                ", empId=" + empId +
                ", task=" + task +
                ", taskName='" + taskName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
