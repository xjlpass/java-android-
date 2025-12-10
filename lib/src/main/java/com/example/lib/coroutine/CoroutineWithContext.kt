package com.example.lib.coroutine

import kotlinx.coroutines.*

// 主线程没有被阻塞，挂起只影响协程
/*
* withContext 不会阻塞线程，只是挂起调用它的协程。
如果在 runBlocking 中调用，它会“看起来阻塞主线程”，因为 runBlocking 会阻塞线程直到协程完成。
在普通协程中调用 withContext，主线程仍然可以自由执行其他任务。*/
fun main() = runBlocking {
    println("主线程开始: ${Thread.currentThread().name}")

    // 默认是 Dispatchers.Default
    launch {
        /*
        * Main = 主线程，用于 UI 操作，不执行耗时任务
        Default = CPU 密集型
        IO = IO 阻塞型
        原则：UI 更新用 Main，计算用 Default，IO 用 IO*/

        /*
        * Dispatchers.IO 是一个 可扩展线程池，可以创建比 CPU 核心更多的线程。

            如果用 Dispatchers.Default：

            线程数量有限（≈ CPU 核心数），长时间阻塞 IO 会导致 CPU 计算任务等待，降低效率。

            用 Dispatchers.IO：

            阻塞 IO 不占用 Default 的 CPU 线程

            可以同时执行大量 IO 操作，不会阻塞其他协程
        * */
        val result = withContext(Dispatchers.IO) {
            println("IO 协程开始: ${Thread.currentThread().name}")
            delay(1000) // 使用挂起函数替代 Thread.sleep
            println("IO 协程结束: ${Thread.currentThread().name}")
            "从 IO 返回的结果"
        }
        println("协程收到结果: $result")
    }

    // 为什么先打印 "主线程继续运行: main" 再打印 "IO 协程开始: DefaultDispatcher-worker-1"
    // 首先协程不阻塞; launch 内的协程刚提交到调度器，还没开始执行;IO 线程调度存在微小延迟（协程调度需要时间）
    println("主线程继续运行: ${Thread.currentThread().name}")
}
