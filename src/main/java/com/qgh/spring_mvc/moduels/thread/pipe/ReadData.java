package com.qgh.spring_mvc.moduels.thread.pipe;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/1915:28
 */
public class ReadData {
    public void readMethod(PipedInputStream input){
        try {
            System.out.println("read:");
            byte [] byteArray=new byte[1024];
            int readLength=input.read(byteArray);
           while(readLength!=-1){
                String newData=new String(byteArray,0,readLength);
               System.out.println(newData);
              readLength=input.read(byteArray);
            }
            System.out.println();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
