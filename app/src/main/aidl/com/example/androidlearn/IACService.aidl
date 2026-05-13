// IACService.aidl
package com.example.androidlearn;

// Declare any non-default types here with import statements

interface IACService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     /*
     这是一个示例方法，告诉开发者：✅ AIDL 支持哪些基础数据类型
     支持的 6 种基础类型：
     int → 整数
     long → 长整数
     boolean → 布尔
     float → 单精度浮点
     double → 双精度浮点
     String → 字符串
     关键点
       AIDL 只支持特定数据类型
       这个方法就是告诉你：基础类型就用这 6 种
       方法不能有修饰符（public/private 都不要写）
       必须定义在 .aidl 文件中
       编译后系统会自动生成 IACService.java 实现类
     basicTypes 可以删除
       basicTypes 只是 Android Studio 新建 AIDL 文件时自动生成的示例占位方法，没有任何系统依赖、没有强制绑定
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
/*
    Binder IPC 的调用涉及 客户端（调用方）和服务端（提供方）：
    角色  	类 / 对象	        所在位置	作用
    Stub	IACService.Stub	    服务端	接收客户端通过 Binder 发来的请求，解析参数，调用真正的方法
    Proxy	IACService.Proxy	客户端	代理客户端调用，把方法调用打包成 Binder 消息发送到远程进程
    */
     void setTemperature(int temp);
     int getTemperature();
     void turnOn();
     void turnOff();
     boolean isOn();
}