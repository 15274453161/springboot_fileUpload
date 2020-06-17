package com.qgh.spring_mvc.moduels.dao;

import com.qgh.spring_mvc.common.util.page.Page;
import com.qgh.spring_mvc.moduels.bean.OrgBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/1714:08
 */

public interface OrgDao extends BaseDao<OrgBean> {
    List<OrgBean> queryPage(Page page);
    List<OrgBean> query(OrgBean orgBean);
}
