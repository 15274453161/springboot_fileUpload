package com.qgh.spring_mvc.moduels.bean;

import com.qgh.spring_mvc.moduels.enums.BookType;
import lombok.Data;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2210:37
 */
@Data
public class Book {
    private int id;
    private String name;
    //这两属性都是枚举,一样的,这个Id和Name只是为了在这个实验里对照数据库中的列方便一些
    private BookType typeId;
    private BookType typeName;

    public Book(){}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", typeName=" + typeName +
                '}';
    }

    public Book(int id, String name, BookType typeId, BookType typeName) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
    }
}
