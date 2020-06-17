package com.qgh.spring_mvc.common.util.thread;
/***
 * 相当于一个生产者的线程抽象类 调用了生产者的方法
 * @return
 * @date 2020/5/12  16:36
 */

public abstract class AbstractProducer implements Producer, Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}