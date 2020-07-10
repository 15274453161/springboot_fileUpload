package com.qgh.spring_mvc.moduels.controller;

import com.qgh.spring_mvc.common.vo.Result;
import com.qgh.spring_mvc.moduels.bean.Employee;
import com.qgh.spring_mvc.moduels.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2314:38
 */
@RestController
@RequestMapping("/mapping")
public class MappingController {
   /* @Autowired
    private EmployeeDao employeeDao;
*/
    /* @GetMapping  (value = "/get")
    public Result get(){
        Employee employee = employeeDao.getEmployee(1);
        return new Result("200","操作成功",employee);
       // return "lll";
    }*/

  /*  @PostMapping(value = "/get0",produces = "application/json")
    public Result get(@RequestBody Employee employee0){
        Employee employee = employeeDao.getEmployee(1);
        return new Result("200","操作成功",employee);
        // return "lll";
    }*/

}
