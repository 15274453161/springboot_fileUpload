package com.qgh.spring_mvc.moduels.virtual.jdk2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 秦光泓
 * @title:模拟堆溢出
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/1414:39
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
       /* List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }*/
       /* byte[] bytes = null;
        for (int i = 0; i < 100; i++) {
            bytes = new byte[1 * 1024 * 1024];
        }*/

        System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");          //系统的最大空间
        System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");    //系统的空闲空间
        System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");  //当前可用的总空间
    }
}
