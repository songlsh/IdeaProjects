package com.sls.jdk8.completAbleFuture;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyTest {

    /**
     * @describe  future 局限性   无法直接表述多个future结果之间的依赖性
     *
     * 无法解决一下之类问题
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(()->{
         return new Random().nextInt();
        });

        executorService.shutdown();

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    /**
     * 日常需求：
     * 将两个异步计算合并为一个（这两个异步计算之间相互独立，同时第二个又依赖于第一个的结果）
     * 等待 Future 集合中的所有任务都完成。
     * 仅等待 Future 集合中最快结束的任务完成，并返回它的结果。
     */


}
