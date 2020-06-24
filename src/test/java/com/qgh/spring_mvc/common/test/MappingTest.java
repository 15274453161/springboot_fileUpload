package com.qgh.spring_mvc.common.test;


import com.google.gson.Gson;
import com.qgh.spring_mvc.common.util.SqlSessionFactoryUtils;
import com.qgh.spring_mvc.moduels.bean.Employee;

import com.qgh.spring_mvc.moduels.dao.EmployeeDao;
import com.qgh.spring_mvc.moduels.dao.EmployeeTaskDao;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * @author 秦光泓
 * @title:映射器
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MappingTest {
    @Autowired
    private EmployeeTaskDao employeeTaskDao;
    @Autowired
    private EmployeeDao employeeDao;


   /* @Test
    public void test1(){
        List<EmployeeTask> employeeTask = employeeTaskDao.getEmployeeTaskByEmpId(1);
        System.out.println(employeeTask);
    }*/

   /* @Test
    public void test1(){
       Employee employee = employeeDao.getEmployee(1);
       //System.out.println(employee);
        //JSONObject jsonObject = JSONObject.fromObject(employee);
        String jsonObject = new   Gson().toJson(employee);
         System.out.println(jsonObject);
    }*/

    @Test
    public void test1(){
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        EmployeeDao em = sqlSession.getMapper(employeeDao.getClass());

    }
}
