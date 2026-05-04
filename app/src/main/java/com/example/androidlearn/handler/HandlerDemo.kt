package com.example.androidlearn.handler

import android.os.Handler
import android.os.Looper
import android.util.Log

// 该类实现子线程通信
// 1 looper准备  2 赋值handler对象 3 handler的处理 4 looper.loop()
class HandlerDemo : Thread() {
    lateinit var childHandler: Handler
        private set

    override fun run() {
        // looper准备
        Looper.prepare()
        childHandler = Handler(Looper.myLooper()!!) { msg ->
            // msg.what 是 Android Handler 消息的类型标识（int 数字）
            when (msg.what) {
                1 -> {
                    Log.i(TAG,"这是子线程处理信息")
                    // true = 这条消息我处理了
                    // false = 这条消息我没处理
                    true
                }
                else ->false
            }
        }
        // 子线程不像主线程自带loop
        Looper.loop()
    }


    // companion object 是 Kotlin 里的「伴生对象」，用来写类似 Java 静态变量、静态方法的代码，直接用「类名。成员」调用，不用创建对象
    // 一个类只能有一个 companion object
    // 里面的成员 不需要创建对象，直接用「类名.」调用
    // 定义常量必须加 const val（编译时常量，效率更高）
    // 它本质是一个单例对象，但写法更简洁
    companion object {
        const val TAG = "HandlerDemo"
    }
    // 静态变量的作用和生命周期
    // 一、静态变量是什么
        // 被 static 修饰的变量（Kotlin 伴生对象 const val/val 等效静态）。
        // 不属于对象，属于类
    // 二、静态变量的作用
        // 全局共享
            // 同类里：所有对象共用同一个值
            // 跨类里：别的类可以直接访问、使用、修改
            // 静态变量的全局，指的就是：整个 App 范围内都能访问共享，不局限当前类
        // 所有 new 出来的对象，共用同一个静态变量，改一处全类所有对象都变。
        // 不用 new 对象就能访问
        // 直接 类名。静态变量 调用，节省创建对象开销。
        // 做常量标识
        // 比如 Handler 的 msg.what 常量、接口返回码、请求地址。
        // 全局状态保存
        // 登录状态、全局配置、全局单例缓存数据。
        // 数据跨页面 / 跨类共享
        // Activity、工具类之间直接拿静态变量传值
    // 三、静态变量生命周期（重点，面试常问）
        // 一句话
        // 跟随类的加载而诞生，跟随类的卸载而销毁，比对象活得久。
        // 完整流程
            // 1 加载时机
            // 类被 JVM 加载到内存时，静态变量就初始化了，早于普通成员变量、早于构造方法。
            // 2 存活范围
            // 对象可以多次 new、销毁
            // 静态变量一直存在，不会随对象销毁而消失
            // 整个 App 运行期间一直常驻内存
            // 3 销毁时机
            // 普通对象：GC 回收就没了
            // 静态变量：只有 App 进程结束 / 类被卸载 才会释放内存

}