package com.qgh.spring_mvc.moduels.thread.pipe;

import java.io.PipedOutputStream;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/1915:33
 */
public class ThreadWrite extends  Thread{
    private WriteData writeData;
    private PipedOutputStream pipedOutputStream;
    public  ThreadWrite(WriteData writeData,PipedOutputStream pipedOutputStream){
        this.writeData=writeData;
        this.pipedOutputStream=pipedOutputStream;
    }

    @Override
    public void run() {
        writeData.writeMethod(pipedOutputStream);
    }
}
