package com.qgh.spring_mvc.common.test.page;

import com.qgh.spring_mvc.common.util.page.Page;
import com.qgh.spring_mvc.moduels.bean.OrgBean;
import com.qgh.spring_mvc.moduels.service.OrgService;
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
 * @date 2020/6/1714:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PageTest {
    @Autowired
    private OrgService orgService;
    private static Logger logger= LoggerFactory.getLogger(PageTest.class);

    @Test
    public void testPage() {
        /**默认首页1 显示15条记录*/
        Page<OrgBean> page = new Page();
        OrgBean orgBean = new OrgBean();
        orgBean.setPage(page);
        List<OrgBean> orgBeans = orgService.queryPage(page);
        //logger.warn("分页结果",orgBeans);
        System.out.println(orgBeans);
    }

   /* @Test
    public void testPage0() {
        *//**默认首页1 显示15条记录*//*

        OrgBean orgBean = new OrgBean();
        List<OrgBean> orgBeans = orgService.query(orgBean);
        System.out.println(orgBeans);
    }*/
}
