package com.example.lib.singleton

/*
单例（Singleton）就是 整个应用程序中只有一个实例
    提供一个 全局访问点
    Kotlin 内置了 object 关键字来定义单例类，比 Java 写法简洁很多
    内部线程安全，由 JVM 类加载机制保证

* */
object SingletonKotlin {
    val name = "Kotlin Singleton"

    fun doSomething() {
        println("Hello from $name")
    }
}

// 带初始化的单例
object DatabaseManager {
    val connection: String

    init {
        println("初始化数据库连接")
        connection = "DBConnection"
    }

    fun query(sql: String) {
        println("执行查询：$sql")
    }
}

// 使用 companion object（类级别单例）
// 如果你希望单例行为属于类，但不是整个程序唯一
class MyClass {
    companion object {
        val INSTANCE = MyClass()

        fun doSomething() {
            println("Companion object method")
        }
    }
}

// 使用 by lazy 实现懒加载单例
// 问题:kotlin object 默认是懒加载,和lazy的区别
// object 的懒加载 第一次访问 Singleton 时初始化
// lazy 是 针对单个属性 的懒加载，而不是整个对象：
//  object 类+ lazy属性: 类初始化加载 但是属性不加载 等第一次使用加载
// object 类 没有 lazy属性: 类加载同时加载类中属性
class LazySingleton private constructor() {

    companion object {
        val instance: LazySingleton by lazy {
            LazySingleton()
        }
    }
}
