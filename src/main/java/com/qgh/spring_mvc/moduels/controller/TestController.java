package com.qgh.spring_mvc.moduels.controller;

import com.qgh.spring_mvc.common.controller.BaseController;
import com.qgh.spring_mvc.common.vo.Result;
import com.qgh.spring_mvc.moduels.bean.Book;
import com.qgh.spring_mvc.moduels.bean.Employee;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
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
@Slf4j
public class TestController  extends BaseController {

    @PostMapping(value = "/qgh",produces = "application/json")
    public Result test1(@RequestBody Book book){
       log.info("测试解密 ",book);
        return new Result(BaseController.RESULT_MESSAGE_ERROR,BaseController.TEMPLATE_ERROR_MESSAGE,book);
    }

}
