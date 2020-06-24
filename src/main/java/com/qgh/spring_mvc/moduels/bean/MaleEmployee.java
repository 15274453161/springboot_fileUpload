package com.qgh.spring_mvc.moduels.bean;

import lombok.Data;

/**
 * @author 秦光泓
 * @title:男雇员
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:29
 */
@Data
public class MaleEmployee extends Employee{
    private MaleHealthForm maleHealthForm = null;

    @Override
    public String toString() {
        return "MaleEmployee{" +
                "Employee{" +
                "id=" + getId() +
                ", realName='" + getRealName() + '\'' +
                ", sex=" + getSex() +
                ", birthday=" + getBirthday() +
                ", mobile='" + getMobile() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", position='" + getPosition() + '\'' +
                ", note='" + getNote() + '\'' +
                ", workCard=" + getWorkCard() +
                ", emplyeeTaskList=" + getEmplyeeTaskList() +
                '}'+
                "maleHealthForm=" + maleHealthForm +
                '}';
    }
}
