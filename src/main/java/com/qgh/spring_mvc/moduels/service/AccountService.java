package com.qgh.spring_mvc.moduels.service;

import com.qgh.spring_mvc.moduels.bean.AccountBean;

import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/910:16
 */
public interface AccountService {
    List<AccountBean> getAccountInfo();
}
