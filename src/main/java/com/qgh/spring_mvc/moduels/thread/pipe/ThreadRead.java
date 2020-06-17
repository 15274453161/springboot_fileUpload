package com.qgh.spring_mvc.moduels.thread.pipe;

import java.io.PipedInputStream;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/1915:36
 */
public class ThreadRead extends Thread {
    private ReadData readData;
    private PipedInputStream pipedInputStream;
    public ThreadRead(ReadData readData,PipedInputStream pipedInputStream){
        this.readData=readData;
        this.pipedInputStream=pipedInputStream;
    }

    @Override
    public void run() {
        readData.readMethod(pipedInputStream);
    }
}
