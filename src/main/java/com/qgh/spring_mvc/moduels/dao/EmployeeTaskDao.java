package com.qgh.spring_mvc.moduels.dao;

import com.qgh.spring_mvc.moduels.bean.EmployeeTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:45
 */
public interface EmployeeTaskDao {
    List<EmployeeTask> getEmployeeTaskByEmpId(@Param("empId") long empId);
}
