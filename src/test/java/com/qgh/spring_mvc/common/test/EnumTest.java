package com.qgh.spring_mvc.common.test;

import com.qgh.spring_mvc.moduels.bean.Book;
import com.qgh.spring_mvc.moduels.dao.BookDao;
import com.qgh.spring_mvc.moduels.enums.BookType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2213:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EnumTest {
    @Autowired
    private BookDao bookDao;
    @Test
    public void test1(){
        //建立一本书,注意后面两个类别属性其实是一样的,只是为了测试使用不同的枚举类型处理器他们在数据库中的不同表现
        Book book1=new Book();
        book1.setId(1);
        book1.setName("一本书");
        book1.setTypeId(BookType.ART);
        book1.setTypeName(BookType.ART);
        //插入,这里顺便测试一下带返回值能不能正确显示受影响行数
        int rows=bookDao.addBook(book1);
        System.out.println("成功插入,"+rows+"行受影响");
        book1.setId(2);
        book1.setName("一本书");
        book1.setTypeId(BookType.ART);
        book1.setTypeName(BookType.ART);
        //插入,这里顺便测试一下带返回值能不能正确显示受影响行数
        int rows0=bookDao.addBook(book1);
        System.out.println("成功插入,"+rows0+"行受影响");
       /* //查出来
        Book book2=bookDao.getBook(1);
        if (book2 != null){
            System.out.println(book2.getTypeId()+"和"+book2.getTypeName()+"都能正常变回枚举类型");
        }*/

    }
}
