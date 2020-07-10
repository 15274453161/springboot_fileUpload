package com.qgh.spring_mvc.common.test.xml;

import com.qgh.spring_mvc.common.util.JaxbUtil;
import com.qgh.spring_mvc.moduels.bean.GetBanksRetBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 秦光泓
 * @title:xml和JavaBean的转换
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/1015:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class XMLConvertTest {
    /***
    * 测试xml转换为JavaBean
    * @return void
    * @date 2020/7/10  15:27
    */

    /*@Test
    public void xmlToJavaBean(){
        String xml ="<?xml version= '1.0' encoding='utf-8'?>\n" +
                "<user>\n" +
                " <ERR>OK</ERR>\n" +
                " <数量>1</数量>\n" +
                " <时间>20200706135057</时间>\n" +
                " <ROW>\n" +
                "  <AAB301>440100</AAB301>\n" +
                "  <AAZ500>AB9591955</AAZ500>\n" +
                "  <AAC002>110107197204026059</AAC002>\n" +
                "  <AAC003>陈三</AAC003>\n" +
                "  <FKRQ>20170628</FKRQ>\n" +
                "  <YXQZ>20270628</YXQZ>\n" +
                "  <AAZ502>1</AAZ502>\n" +
                "  <AAE008>95566</AAE008>\n" +
                "  <AAE008B>308581002013</AAE008B>\n" +
                "  <AAE010>6214832014247858</AAE010>\n" +
                "  <AAE010A>6214832014247858</AAE010A>\n" +
                "  <AAE010B>6214832014247858</AAE010B>\n" +
                "  <GETTIME>20170629000000</GETTIME>\n" +
                "  <GETTIME1></GETTIME1>\n" +
                "  <KFWM>0090CF31028649440109330225</KFWM>\n" +
                "  <AAZ501>440100D156000005023B1B25B3135362</AAZ501>\n" +
                " </ROW>\n" +
                "</user>";
        GetBanksRetBean getBanksRetBean = JaxbUtil.converyToJavaBean(xml,GetBanksRetBean.class);
        System.out.println(getBanksRetBean);
    }*/
}
