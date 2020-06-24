package com.qgh.spring_mvc.moduels.dao;

import com.qgh.spring_mvc.moduels.bean.Book;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2210:42
 */
public interface BookDao {
    Book getBook(int id);
    int addBook(Book book);
}
