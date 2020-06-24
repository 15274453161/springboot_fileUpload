package com.qgh.spring_mvc.moduels.enums;

/**
 * @author 秦光泓
 * @title:性别的枚举
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:34
 */
public enum  SexEnum {
    MALE(1,"男"),FEMALE(2,"女");
    private int id;
    private String name;

    private SexEnum(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static SexEnum getSex(int id){
        if(id == 1){
            return MALE;
        }else if(id == 2){
            return FEMALE;
        }
        return null;
    }
}
