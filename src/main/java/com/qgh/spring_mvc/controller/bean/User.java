package com.qgh.spring_mvc.controller.bean;

import com.qgh.spring_mvc.util.ExcelAttribute;
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
    @ExcelAttribute(name = "姓名",column = "A")
    private String name;
    @ExcelAttribute(name = "密码",column = "B")
    private String password;
    @ExcelAttribute(name = "性别",column = "C")
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
