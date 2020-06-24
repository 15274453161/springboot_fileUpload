package com.qgh.spring_mvc.moduels.bean;

import lombok.Data;

/**
 * @author 秦光泓
 * @title:体检表父类
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:21
 */
@Data
public abstract class HealthForm {
    private Long id;
    private Long empId;
    private String heart;
    private String liver;
    private String spleen;
    private String lung;
    private String kidney;
    private String note;

    @Override
    public String toString() {
        return "HealthForm{" +
                "id=" + id +
                ", empId=" + empId +
                ", heart='" + heart + '\'' +
                ", liver='" + liver + '\'' +
                ", spleen='" + spleen + '\'' +
                ", lung='" + lung + '\'' +
                ", kidney='" + kidney + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
