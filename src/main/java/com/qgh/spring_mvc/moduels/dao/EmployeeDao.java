package com.qgh.spring_mvc.moduels.dao;

import com.qgh.spring_mvc.moduels.bean.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2311:41
 */
public interface EmployeeDao {
    Employee getEmployee(@Param("id") long id);
}
