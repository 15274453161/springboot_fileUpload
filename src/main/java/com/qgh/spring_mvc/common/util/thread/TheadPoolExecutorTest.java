package com.qgh.spring_mvc.common.util.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/149:55
 */
public class TheadPoolExecutorTest {
    public static void main(String[] args) {
        //核心线程数5，最大线程数10，阻塞队列采用ArrayBlockingQueue,做多排队5个
        ThreadPoolExecutor executor =new ThreadPoolExecutor(5,10,200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
       executor.shutdown();


    }
}
