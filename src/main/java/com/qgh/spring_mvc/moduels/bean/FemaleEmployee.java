package com.qgh.spring_mvc.moduels.bean;

import lombok.Data;

/**
 * @author 秦光泓
 * @title:女雇员
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:30
 */
@Data
public class FemaleEmployee extends Employee{
    private FemaleHealthForm femaleHealthForm = null;

    @Override
    public String toString() {
        return "FemaleEmployee{" +
                "femaleHealthForm=" + femaleHealthForm +
                '}';
    }
}
