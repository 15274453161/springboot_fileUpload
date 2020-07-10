package com.qgh.spring_mvc.common.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2815:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CodeUtil {
    @Test
    public void testProxy0() throws IOException {
       /* byte[]classFile = ProxyGenerator.generateProxyClass("Proxy0",HumenImpl1.class.getInterfaces());

        File file =new File("D:/test/demo/AOP/aoptest/demoaopproxy/Proxy0.class");

        FileOutputStream fos =new FileOutputStream(file);

        fos.write(classFile);

        fos.flush();

        fos.close();*/

    }
}
