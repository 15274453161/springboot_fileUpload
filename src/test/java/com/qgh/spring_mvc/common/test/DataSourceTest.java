package com.qgh.spring_mvc.common.test;

import com.qgh.spring_mvc.moduels.bean.OrgBean;
import com.qgh.spring_mvc.moduels.service.OrgService;
import com.qgh.spring_mvc.moduels.service.impl.OrgServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/918:28
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class DataSourceTest {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
    @Autowired
    private OrgService orgService;
    @Test
    public void test1(){

      List<OrgBean> orgBeans = orgService.query4cssp(new OrgBean());
        logger.info("查询结果 ",orgBeans);
        System.out.println(orgBeans);

    }
}
