package com.qgh.spring_mvc.common.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/4/2415:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestFilter {

    //声明
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void init(){
        request=new MockHttpServletRequest();
        request.setCharacterEncoding("utf-8");
        response=new MockHttpServletResponse();
    }

    @Test
    public void testFilter(){

    }
}
