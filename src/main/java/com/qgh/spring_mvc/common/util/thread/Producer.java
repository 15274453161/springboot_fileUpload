package com.qgh.spring_mvc.common.util.thread;

/***
*生产者接口------>生产的方法
* @return
* @date 2020/5/12  16:35
*/

public interface Producer {
  void produce() throws InterruptedException;
}