package com.example.lib.singleton;

// 懒汉式+双重检查锁DCL
public class LazySingletonDemo2 {

    // 即使你用双重检查 DCL，如果不加 volatile，也可能出现指令重排问题，让另一个线程拿到一个未初始化完成的对象。
    // 半初始化状态: 象的内存已经分配了，但构造函数还没跑完，对象还没完全准备好.对象还没有构造完,引用已经对外可见,其他线程可能拿到一个没有初始化完的对象
    // volatie 如何实现禁止重排序的,强制让new的对象三步骤按顺序执行
    /*
    *       ① 分配内存
            ② 初始化（构造方法）
            ③ instance 指向内存（对外可见）
    */
    // 如果不加 volatile 需要在 下方 getInstance 方法中实用关键字synchoronized(可以不用下面的双重校验,实现简单,但是性能一般)
    private static volatile LazySingletonDemo2 lazySingletonInstance;

    private LazySingletonDemo2() {
    }

    private static LazySingletonDemo2 getInstance() {
        // 第一次检查：避免不必要的加锁
        // 如果单例对象已经存在，没必要进入 synchronized：
        if (lazySingletonInstance == null) {
            // synchronized 的核心作用: 它是 Java 提供的最基本的“线程互斥锁”，防止多线程同时操作同一份数据，从而避免竞态问题。
            synchronized (LazySingletonDemo2.class) {
                /*
                * 多个线程可能同时通过第一次检查（外层 if）
                    之后排队进入 synchronized
                    第一个线程创建了对象
                    后面的线程进入锁内部时如果不检查，会再创建一次对象
                * */
                if (lazySingletonInstance == null) {
                    lazySingletonInstance = new LazySingletonDemo2();
                }
            }
        }
        return lazySingletonInstance;
    }
}
