package com.qgh.spring_mvc.moduels.bean;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 秦光泓
 * @title:
 * @projectName CommonService
 * @description: TODO
 * @date 2020/7/616:10
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ROW")
public class BankRowBean {

    @XmlElement(name = "AAB301")
    private String aab301;
    @XmlElement(name = "AAZ500")
    private String aaz500;
    @XmlElement(name = "AAC002")
    private String aac002;
    @XmlElement(name = "AAC003")
    private String aac003;
    @XmlElement(name = "FKRQ")
    private String fkrq;
    @XmlElement(name = "YXQZ")
    private String yxqz;
    @XmlElement(name = "AAZ502")
    private String aaz502;
    @XmlElement(name = "AAE008")
    private String aae008;
    @XmlElement(name = "AAE008B")
    private String aae008b;
    @XmlElement(name = "AAE010")
    private String aae010;
    @XmlElement(name = "AAE010A")
    private String aae010a;
    @XmlElement(name = "AAE010B")
    private String aae010b;
    @XmlElement(name = "GETTIME")
    private String gettime;
    @XmlElement(name = "GETTIME1")
    private String gettime1;
    @XmlElement(name = "KFWM")
    private String kfwm;
    @XmlElement(name = "AAZ501")
    private String aaz501;
}
