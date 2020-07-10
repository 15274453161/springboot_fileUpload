package com.qgh.spring_mvc.moduels.service.impl;

import com.qgh.spring_mvc.moduels.bean.AccountBean;
import com.qgh.spring_mvc.moduels.dao.AccountDao;
import com.qgh.spring_mvc.moduels.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/910:17
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Override
    public List<AccountBean> getAccountInfo() {
        return accountDao.getAccountInfo();
    }
}
