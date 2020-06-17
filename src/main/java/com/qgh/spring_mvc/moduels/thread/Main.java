package com.qgh.spring_mvc.moduels.thread;

import com.qgh.spring_mvc.moduels.thread.pipe.ReadData;
import com.qgh.spring_mvc.moduels.thread.pipe.ThreadRead;
import com.qgh.spring_mvc.moduels.thread.pipe.ThreadWrite;
import com.qgh.spring_mvc.moduels.thread.pipe.WriteData;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/1317:40
 */
public class Main {
    /*public static void main(String[] args) {

        try {
            WriteData writeData=new WriteData();
            ReadData readData=new ReadData();

            PipedInputStream pipedInputStream=new PipedInputStream();
            PipedOutputStream pipedOutputStream=new PipedOutputStream();

            pipedOutputStream.connect(pipedInputStream);
            ThreadRead threadRead=new ThreadRead(readData,pipedInputStream);
            threadRead.start();
            Thread.sleep(1000);
            ThreadWrite threadWrite=new ThreadWrite(writeData,pipedOutputStream);
            threadWrite.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static ThreadLocal t1 = new ThreadLocal<>();
    public static void main(String[] args) {
        if(t1.get()==null){
            System.out.println("从未放过值");
            t1.set("放入了值");
        }
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

}
