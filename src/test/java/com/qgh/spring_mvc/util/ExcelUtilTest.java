package com.qgh.spring_mvc.util;

import com.qgh.spring_mvc.controller.bean.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/4/317:36
 */
class ExcelUtilTest {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtilTest.class);
    @Test
    void getExcelToList() {
        // 初始化数据
        List<User> list = new ArrayList<User>();

        User vo = new User();
        vo.setId(1);
        vo.setName("qgh");
        vo.setPassword("123456yfhgjvnffhryfkflnjk汉子");
        vo.setSex(1);
        list.add(vo);
        FileOutputStream out = null;
        try {
            //这个的作用
            out = new FileOutputStream("d:\\ExcelUtiltext.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelUtil<User> util = new ExcelUtil<>(User.class);// 创建工具类.
        util.getListToExcel(list, "用户信息", out);// 导出
        logger.info("----执行完毕----------");
    }

    @Test
    void getListToExcel() {
    }
}