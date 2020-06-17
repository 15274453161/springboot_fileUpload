package com.qgh.spring_mvc.common.util.thread;

/***
*接口的生产者和消费者线程
* @return
* @date 2020/5/12  16:39
*/

public interface Model {
  Runnable newRunnableConsumer();
  Runnable newRunnableProducer();
}