package com.qgh.spring_mvc.moduels.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/209:21
 */
public class MyService {
    /*private Lock lock = new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void testMethod() {
        try {
            lock.lock();
            System.out.println(" await的时间"+System.currentTimeMillis());
            condition.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("llllljjjkh");
          //  lock.unlock();
        }

        *//*for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName=" + Thread.currentThread().getName()
                    + (" " + (i + 1)));
        }*//*
        //lock.unlock();
    }

    public  void signal(){
        try{
            lock.lock();
            System.out.println(" 唤醒的时间"+System.currentTimeMillis());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }*/
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;
    //--------生产者--------------
    public void set() {
        try {
            lock.lock();
            while (hasValue == true) {
                System.out.println("有可能**连续");
                condition.await();
            }
            System.out.println("打印**");
            hasValue = true;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    //---------消费者-------------
    public void get() {
        try {
            lock.lock();
            while (hasValue == false) {
                System.out.println("有可能--连续");
                condition.await();
            }
            System.out.println("打印--");
            hasValue = false;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
