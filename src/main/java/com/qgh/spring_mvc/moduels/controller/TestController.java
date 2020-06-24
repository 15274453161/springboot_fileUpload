package com.qgh.spring_mvc.moduels.controller;

import com.qgh.spring_mvc.common.controller.BaseController;
import com.qgh.spring_mvc.common.vo.Result;
import com.qgh.spring_mvc.moduels.bean.Employee;
import org.springframework.web.bind.annotation.*;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/4/2416:14
 */
@RestController
@RequestMapping("/test")
public class TestController  extends BaseController {
    @RequestMapping(value = "/qgh",produces = "application/json")
    public Result test1(){
        return new Result(BaseController.RESULT_MESSAGE_ERROR,BaseController.TEMPLATE_ERROR_MESSAGE,null);
    }

}
