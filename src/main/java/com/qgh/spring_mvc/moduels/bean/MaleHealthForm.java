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
public class MaleHealthForm extends HealthForm {
    private String prostate;

    @Override
    public String toString() {
        return "HealthForm{{" +
                "id=" + getId() +
                ", empId=" + getEmpId() +
                ", heart='" + getHeart() + '\'' +
                ", liver='" + getLiver() + '\'' +
                ", spleen='" + getSpleen() + '\'' +
                ", lung='" + getLung() + '\'' +
                ", kidney='" + getKidney() + '\'' +
                ", note='" + getNote() + '\'' +
                '}'+
                "MaleHealthForm{" +
                "prostate='" + prostate + '\'' +
                "}}";
    }
}
