package com.qgh.spring_mvc.moduels.bean;

import lombok.Data;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:22
 */
@Data
public class FemaleHealthForm extends HealthForm {
    private String uterus;

    @Override
    public String toString() {
        return "FemaleHealthForm{" +
                "uterus='" + uterus + '\'' +
                '}';
    }
}
