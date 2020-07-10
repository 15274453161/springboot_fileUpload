package com.qgh.spring_mvc.common.util;

import com.qgh.spring_mvc.moduels.bean.AccountBean;
import com.qgh.spring_mvc.moduels.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/4/317:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ExcelUtilTest {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JedisPool jedisPool;
    private static Logger logger = LoggerFactory.getLogger(ExcelUtilTest.class);
    @Autowired
    AccountService accountService;
   /* @Test
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
    }*/

   /* @Test
    void getListToExcel() {
        redisUtil.set("123","20200421");
        System.out.println(redisUtil.get("123"));

    }*/

     @Test
    void getExcelToList() {
        // 初始化数据
        List<AccountBean> list = accountService.getAccountInfo();

        FileOutputStream out = null;
        try {
            //这个的作用
            out = new FileOutputStream("d:\\userAccount.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelUtil<AccountBean> util = new ExcelUtil<>(AccountBean.class);// 创建工具类.
        util.getListToExcel(list, "用户信息", out);// 导出
        logger.info("----执行完毕----------");
    }
}