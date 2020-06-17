package com.qgh.spring_mvc.common.util.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 循环产生消费(获取最新页数据量不够时，休眠sleepTimes毫秒，继续从第1页开始产生数据)
 * @param <T>   产品bean
 */
@Slf4j
public abstract class PCModel<T> implements Model {
    protected BlockingQueue<T> queue;
    protected final AtomicInteger increTaskNo = new AtomicInteger(0);
    protected int pagesize = 1000;//默认：生产数据页大小
    protected static int sleepTimes = 1000;//默认：无生产数据休息10s
    private static volatile boolean running = true;
    /**
     * 启动
     * @param cap   产品队列大小(一般设置页数量的10倍)
     * @param threadNb  消费者数量(小于等于页大小，视服务器性能与接口特性决定)
     */
    public void start(int cap, int threadNb) {
        // LinkedBlockingQueue 的队列是 lazy-init 的，但 ArrayBlockingQueue 在创建时就已经 init
        this.queue = new LinkedBlockingQueue<>(cap);
        //启动生产者线程
        new Thread(newRunnableProducer()).start();
        for (int i = 0; i < threadNb; i++) {
            new Thread(newRunnableConsumer()).start();
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    private class ProducerImpl extends AbstractProducer  {
        @Override
        public void produce() throws InterruptedException {
            if(!running){
                throw new InterruptedException();
            }
            List<T> beans = null;
            try {
                beans = getProduce( increTaskNo.getAndIncrement(), pagesize );
                if ( !CollectionUtils.isEmpty(beans)){
                    for (T bean : beans) {
                        queue.put(bean);
                    }
                    beans.clear();
                }
            }catch (Exception e){
                log.error("produce",e);
            }
            if ( !CollectionUtils.isEmpty(beans) || beans.size()<pagesize ){
                increTaskNo.set(0);
                sleep( sleepTimes );
            }
        }
    }


    private class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable {
        @Override
        public void consume() throws InterruptedException {
            if(!running){
                throw new InterruptedException();
            }
            doConsume( queue.take() );
        }
    }

    /**
     * 生产下一页数据
     * @param andIncrement 页码
     * @param pagesize 页大小
     */
    protected abstract List<T> getProduce(int andIncrement, int pagesize);

    /**
     * 取数据进行消费()
     * @param take 待消费数据
     */
    protected abstract void doConsume(T take);

    public boolean sleep(long millis){
        try {
            Thread.sleep( millis );
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}