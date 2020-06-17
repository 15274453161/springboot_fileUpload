package com.qgh.spring_mvc.moduels.thread.lock;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/209:23
 */
public class Run {
   /* public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();

        MyThread a1 = new MyThread(service);
        a1.start();
        Thread.sleep(500);
        service.signal();
    }*/
   public static void main(String[] args) throws InterruptedException {
      /* MyService service = new MyService();
       MyThreadA[] threadA = new MyThreadA[10];
       MyThreadB[] threadB = new MyThreadB[10];
       for (int i = 0; i < 10; i++) {
           threadA[i] = new MyThreadA(service);
           threadB[i] = new MyThreadB(service);
           threadA[i].start();
           threadB[i].start();
       }*/
      /* // 1.1使用匿名内部类
       new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("Hello world !");
           }
       }).start();

       // 1.2使用 lambda 获得Runnable接口对象
       new Thread(() -> System.out.println("Hello world 11111!")).start();*/

       String[] players = {"zhansgan", "lisi", "wangwu", "zhaoliu",  "wangmazi"};

       // 1.1 使用匿名内部类根据 surname 排序 players
       Arrays.sort(players, new Comparator<String>() {
           @Override
           public int compare(String s1, String s2) {
               return (s1.compareTo(s2));
           }
       });

       // 1.2 使用 lambda 排序,根据 surname
       Arrays.sort(players, (String s1, String s2) ->  s1.compareTo(s2));

   }

}
