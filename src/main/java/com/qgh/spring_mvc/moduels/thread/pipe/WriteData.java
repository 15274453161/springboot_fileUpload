package com.qgh.spring_mvc.moduels.thread.pipe;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/1915:23
 */
public class WriteData {
    public void  writeMethod(PipedOutputStream out){
        try {
            System.out.println("write:");
            for (int i=0;i<300;i++){
                String outData=""+(i+1);
                out.write(outData.getBytes());
            }
            System.out.println("====");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
