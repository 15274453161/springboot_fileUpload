package com.qgh.spring_mvc.common.util.thread;
/***
* 相当于一个消费者的线程抽象类 调用了消费者的方法
* 需要具体的消费实现类实现它
* @date 2020/5/12  16:36
*/

public abstract class AbstractConsumer implements Consumer, Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}