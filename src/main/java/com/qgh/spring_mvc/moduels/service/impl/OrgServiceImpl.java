package com.qgh.spring_mvc.moduels.service.impl;

import com.qgh.spring_mvc.common.util.page.Page;
import com.qgh.spring_mvc.moduels.bean.OrgBean;
import com.qgh.spring_mvc.moduels.dao.OrgDao;
import com.qgh.spring_mvc.moduels.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/1714:11
 */
@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OrgDao orgDao;
    @Override
    public List<OrgBean> queryPage(Page page) {
        return orgDao.queryPage(page);
    }

    @Override
    public List<OrgBean> query(OrgBean orgBean) {
        return orgDao.query(orgBean);
    }
}
