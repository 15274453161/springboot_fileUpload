package com.qgh.spring_mvc;

import com.qgh.spring_mvc.common.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringMvcApplicationTests {
    @Autowired
    private RedisUtil redisUtil;
   /* @Test
    void contextLoads() {
    }*/

   /* @Test
    public void testJ(){
        System.out.println(redisUtil);
    }*/

}
