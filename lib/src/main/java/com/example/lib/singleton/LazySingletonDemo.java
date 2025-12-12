package com.example.lib.singleton;

/*
* 懒汉式
* 特点
    只有在第一次调用时才创建实例
    节省资源
    多线程条件下不安全，需要额外处理
 适用场景
    实例创建成本高 + 可能不会使用
* */
public class LazySingletonDemo {

    private static LazySingletonDemo lazySingletonDemo;

    private LazySingletonDemo() {
    }

    public static LazySingletonDemo getInstance() {
        // 线程不安全
        // 不安全原因
        /*
        在 多线程同时调用 getInstance() 时会发生竞争——多个线程都看到 lazySingletonDemo == null，然后都执行创建操作。
        两个线程都创建了一个对象, 导致单例被破坏 ,多个地方拿到的实例不一致.
        * */
        if (lazySingletonDemo == null) {
            lazySingletonDemo = new LazySingletonDemo();
        }
        return lazySingletonDemo;
    }
}
