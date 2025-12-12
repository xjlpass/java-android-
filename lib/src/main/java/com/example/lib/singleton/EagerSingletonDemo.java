package com.example.lib.singleton;

/*
*饿汉式:
* 特点
    类加载时就创建实例
    天生线程安全
    不管你用不用，实例都已经占内存了
适用场景:
    实例创建成本低 + 一定会用到（例如全局配置）
* */
public class EagerSingletonDemo {

    private static final EagerSingletonDemo INSTANCE = new EagerSingletonDemo();

    // 私有构造器,为了控制对象创建方式,初始化参数和校验
    private EagerSingletonDemo() {
    }

    // 注意必须加static
    // 单例对象是“类级别”的，所以访问方法也必须“类级别”。
    public static EagerSingletonDemo getInstance() {
        return INSTANCE;
    }
}
