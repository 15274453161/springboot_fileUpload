package com.qgh.spring_mvc.moduels.bean;

import lombok.Data;

/**
 * @author 秦光泓
 * @title:任务表
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:23
 */
@Data
public class Task {
    private Long id;
    private String title;
    private String context;
    private String note;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
