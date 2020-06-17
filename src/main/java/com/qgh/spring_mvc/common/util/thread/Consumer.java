package com.qgh.spring_mvc.common.util.thread;
/***
*消费者---->有一个消费者的方法
* @return
* @date 2020/5/12  16:35
*/

public interface Consumer {
  void consume() throws InterruptedException;
}