package com.qgh.spring_mvc.moduels.controller;

import com.qgh.spring_mvc.common.util.page.Page;
import com.qgh.spring_mvc.moduels.bean.OrgBean;
import com.qgh.spring_mvc.moduels.service.OrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/1715:08
 */
@RestController()
@RequestMapping("/page")
public class PageController {
    @Autowired
    private OrgService orgService;
    private static Logger logger= LoggerFactory.getLogger(PageController.class);


    @PostMapping(value ="/pageQuery",produces = "appication/json")
    public String pageQuery(){
        /**默认首页1 显示15条记录*/
        Page<OrgBean> page = new Page();
        OrgBean orgBean = new OrgBean();
        orgBean.setPage(page);
        List<OrgBean> orgBeans = orgService.queryPage4cssp(page);
        logger.warn("分页结果",orgBeans);
        return "lll";
    }
}
