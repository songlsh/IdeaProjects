package com.sls.poolThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    //创建一个队列
    private BlockingQueue<Runnable> queue;

    //线程池
    List<Thread> workers;

    //执行队列
    private class Worker extends Thread{

        private ThreadPool pool;

        private Runnable task = null;
        public Worker( ThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while(isWorking || this.pool.queue.size()>0){
                try {
                    if(isWorking){
                        task = this.pool.queue.take();  //从队列中取出任务来
                    }else {
                        task = this.pool.queue.poll();                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(task != null){
                    task.run();   //如果任务不为null，执行任务
                    System.out.println("执行任务："+ Thread.currentThread().getName()+"执行完毕");
                }
            }
        }
    }

    /**
     * 初始化线程池
     *
     * @param queueSize  线程池中队列的长度
     * @param poolSize   线程池的大小
     */
    public ThreadPool(int queueSize,int poolSize) {
        if(queueSize <= 0 || poolSize <=0)
            throw new IllegalArgumentException("非法参数");
        this.queue = new LinkedBlockingQueue<>(queueSize);
        this.workers = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(this);
            workers.add(worker);
            worker.start();
        }
    }

    //给执行队列压入任务
    public boolean submit(Runnable runnable){
        if(isWorking){
            return  this.queue.offer(runnable);
        }else {
            return false;
        }
    }

    //销毁线程池
    /**
     * 1）不在往执行队列添加
     * 2) 也不再从执行队列中取
     * 3）已存在队列中的采用非阻塞的方式去取
     * 4）阻塞的线程中断掉
     *
     */
    private volatile boolean isWorking = true;
    public void shutDown(){
        isWorking = false;
        for(Thread thread:workers){
            if(Thread.State.BLOCKED.equals(thread.getState()))
                thread.interrupt();
        }
    }
}
