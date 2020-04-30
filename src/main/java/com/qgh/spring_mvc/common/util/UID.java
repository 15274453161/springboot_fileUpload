package com.qgh.spring_mvc.common.util;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Zetting on 2017/4/8.
 * 生成为一标识符
 */
public class UID {
    private static Date date = new Date();
    private static StringBuilder buf = new StringBuilder();
    private static int seq = 0;
    private static final int ROTATION = 99999;

    /**
     * 年月日时分秒+5位序号
     */
    public static synchronized String dateTimes(){
        if (seq > ROTATION) seq = 0;
        buf.delete(0, buf.length());
        date.setTime(System.currentTimeMillis());
        String str = String.format("%1$ty%1$tm%1$td%1$tk%1$tM%1$tS%02$05d", date, seq++);
        return str;
    }

    /**
     * UUID字符串
     */
    public static synchronized String uuid(){
        String s = UUID.randomUUID().toString().trim().replaceAll("-", "").toUpperCase();
        //去掉“-”符号
        return s;
    }

}
