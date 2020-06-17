package com.qgh.spring_mvc.moduels.thread.lock;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/2010:25
 */
public class MyThreadA extends  Thread {
    private MyService myService;
    public MyThreadA(MyService myService) {
        super();
        this.myService = myService;
    }
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            myService.set();
        }
    }

}
