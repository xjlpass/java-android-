package com.example.lib.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
* Future 阻塞式*/
public class FutureDemo {
    public static void main(String[] args) {
        // ExecutorService 是 并发包 java.util.concurrent()提供的线程池接口,
        // 特点:1 可以复用线程,减少线程创建的开销
        //     2 提交任务并异步获取结果
        //     3 统一管理线程生命周期
        ExecutorService executorSevice = Executors.newSingleThreadExecutor();
        System.out.println("提交任务,主线程运行中...");

        // 提交异步任务,返回Future
        // 提交任务:submit
        // Future<T> submit(Callable<T> task); // 异步执行，有返回值
        // Future<?> submit(Runnable task);    // 异步执行，无返回值
        // Runnable = 执行任务，不关心结果；Callable = 执行任务，需要结果
        Future<String> future = executorSevice.submit(()->{
            long temp = System.currentTimeMillis();
            System.out.println("子线程:开始执行任务"+temp);
            Thread.sleep(1000);
            System.out.println("子线程:任务完成"+(System.currentTimeMillis()-temp));

            return "hello";
        });

        System.out.println("任务已提交，主线程继续执行...");

        // 阻塞线程,获取结果
        try {
            String result = future.get();
            System.out.println("主线程:拿到结果->"+result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // 关闭线程池
        executorSevice.shutdown();
        System.out.println("线程池已经关闭");
    }
}
