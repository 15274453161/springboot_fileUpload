package com.qgh.spring_mvc.moduels.bean;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 秦光泓
 * @title:多银行接口返回
 * @projectName CommonService
 * @description: TODO
 * @date 2020/7/614:17
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
/**name = "value"需要和xml的root根目录的值相同*/
@XmlRootElement(name = "user")
public class GetBanksRetBean {
    /***
     第1行：<ERR>OK</ERR><数量>2</数量><时间>20141226064837</时间>
     当<ERR>域返回“OK”时信息有效，否则<ERR>域为出错信息。<数量>域返回的是卡数量。
     第2行起：
     <ROW><AAB301>发卡城市1</AAB301><AAZ500>卡号1</AAZ500><AAC002>社会保障号1</AAC002><AAC003>姓名1</AAC003><FKRQ>发卡日期1</FKRQ><YXQZ>卡有效期1</YXQZ><AAZ502>卡状态1</AAZ502><AAE008>服务银行1</AAE008><AAE008B>开户行号1</AAE008B><AAE010>金融卡号1</AAE010><AAE010A>金融账号1</AAE010A><AAE010B>个人账户账号1</AAE010B><GETTIME>领卡时间1</GETTIME><GETTIME1>银行激活时间1</GETTIME1><KFWM>**</KFWM><AAZ501>**</AAZ501></ROW>
     返回行中<FKRQ>和<YXQZ>域分别为发卡日期和有效期至，<AAZ502>域为卡状态（为1时有效），使用时综合分析这几个域的值并视业务需求确定是否引用获取的银行信息。
     */

	@XmlElement(name = "ERR")
	private String err;
	@XmlElement(name = "数量")
	private String num;
	@XmlElement(name = "时间")
	private String time;
    @XmlElement(name = "ROW")
    private BankRowBean bankRowBean;



}
