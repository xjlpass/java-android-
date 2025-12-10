package com.example.lib.future;

import static java.lang.Thread.sleep;

import java.util.concurrent.CompletableFuture;

/*
 * FutureDemo
 * 模拟场景:查用户 → 查订单（依赖关系）
 * 打印结果:main 线程没有阻塞
            查到用户
            查到订单
            最终结果:OrderOf-UserA
 *
 * */
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {

        // CompletableFuture 是 Java 8 引入的一种异步任务类，属于 java.util.concurrent 包
        /* 特点:
        表示一个异步任务的结果（可能还没完成）
        可以链式处理，支持 thenApply、thenAccept、thenCompose 等操作
        可以非阻塞地获取结果，不像传统 Future 需要 get() 阻塞线程
        * */
        CompletableFuture<String> userFutrue = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            System.out.println("查到用户");
            return "UserA";
        });

        // thenCompose 链式异步调用，第二个任务依赖第一个结果
            // 功能
            // 在第一个 CompletableFuture 完成后，再执行另一个异步任务
            // 用返回的结果 user 去生成新的 CompletableFuture
            // 返回 一个新的 CompletableFuture，表示整个链的最终结果
        CompletableFuture<String> orderFuture = userFutrue.thenCompose(user ->
                CompletableFuture.supplyAsync(() -> {
                    sleep(2000);
                    System.out.println("查到订单");
                    return "OrderOf-" + user;
                }));

        orderFuture.thenAccept(order -> {
            System.out.println("最终结果:" + order);
        });

        System.out.println("main 线程没有阻塞");

        // 防止线程阻塞
        Thread.sleep(3000);
    }

    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}