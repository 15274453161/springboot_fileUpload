package com.qgh.spring_mvc.common.controller.bean;

import org.apache.ibatis.type.Alias;

/**
 * @author 秦光泓
 * @title:编写和sql对应的实体类，并使用上述注解，来说明列名称。
 * @projectName chaptor5
 * @description: TODO
 * @date 2020/3/313:21
 */
@Alias("user")
public class User {

    private Integer id;
    private String name;
    private String password;
    private int sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                '}';
    }
}
