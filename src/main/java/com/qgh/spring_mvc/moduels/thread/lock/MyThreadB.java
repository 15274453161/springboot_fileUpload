package com.qgh.spring_mvc.moduels.thread.lock;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/2010:26
 */
public class MyThreadB extends Thread {
    private MyService myService;
    public MyThreadB(MyService myService) {
        super();
        this.myService = myService;
    }
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            myService.get();
        }
    }

}
