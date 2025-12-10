package com.example.lib.future;

import java.util.concurrent.CompletableFuture;

/*
功能：对 CompletableFuture 的结果进行 转换，返回一个新的 CompletableFuture。
输入：上一个阶段的结果
输出：转换后的结果
返回类型：CompletableFuture<U>
* */
public class ThenApplyDemo {
    public static void main(String[] args) {
        // 异步执行任务：任务会在 ForkJoinPool.commonPool() 或自定义线程池中执行，不会阻塞调用线程
        // 有返回值：与 runAsync 不同，supplyAsync 可以返回结果
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<Integer> result = future.thenApply(x -> x * 2);
        // 对 CompletableFuture 的结果进行 消费（使用结果，但不返回新的值）
        /*
        * 输入：上一个阶段的结果
        输出：无（返回 CompletableFuture<Void>）
        返回类型：CompletableFuture<Void>*/
        result.thenAccept(System.out::println);

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> 5);

        // 异步依赖扁平化
        CompletableFuture<String> completableFuture1 = completableFuture.thenCompose(x ->
                CompletableFuture.supplyAsync(() -> "结果:" + (x * 2))
        );

        // join()阻塞等待
        completableFuture1.thenAccept(System.out::println).join();
    }
}
