package com.qgh.spring_mvc.moduels.dao;

import com.qgh.spring_mvc.moduels.bean.AccountBean;

import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/99:59
 */
public interface AccountDao {
    List<AccountBean> getAccountInfo();
}
