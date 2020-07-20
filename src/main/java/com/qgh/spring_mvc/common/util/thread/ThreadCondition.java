package com.qgh.spring_mvc.common.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/1410:27
 */

public class ThreadCondition implements Runnable {


    public void testThreadPool(){
        Runtime run=Runtime.getRuntime();//当前程序运行对象
        run.gc();//调用垃圾回收机制，减少内存误差
        Long freememroy=run.freeMemory();//获取当前空闲内存
        Long protime=System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            new Thread(new ThreadCondition()).start();
        }
        System.out.println("独立创建"+10000+"个线程需要的内存空间"+(freememroy-run.freeMemory()));
        System.out.println("独立创建"+10000+"个线程需要的系统时间"+(System.currentTimeMillis()-protime));


        System.out.println("---------------------------------");
        Runtime run2=Runtime.getRuntime();//当前程序运行对象
        run2.gc();//调用垃圾回收机制，减少内存误差
        Long freememroy2=run.freeMemory();//获取当前空闲内存
        Long protime2=System.currentTimeMillis();
        ExecutorService service= Executors.newFixedThreadPool(2);
        for(int i=0;i<10000;i++){
            service.execute(new ThreadCondition()) ;
        }
        System.out.println("线程池创建"+10000+"个线程需要的内存空间"+(freememroy2-run.freeMemory()));
        service.shutdown();

        System.out.println("线程池创建"+10000+"个线程需要的系统时间"+(System.currentTimeMillis()-protime2));




    }
    @Override
    public void run() {

    }


}
