package com.example.lib.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// runBlocking = 把“协程代码”强行变成“同步阻塞代码”
// 它会 阻塞当前线程，直到协程里的代码全部执行完
// runBlocking 会等待协程完成，主线程被阻塞
fun main() = runBlocking {
    println("主线程开始：${Thread.currentThread().name}")

    val time = measureTimeMillis {
        val job = launch {
            println("协程开始：${Thread.currentThread().name}")
            delay(3000) // 模拟耗时操作
            println("协程结束")
        }

        println("等待子协程完成")
        job.join() // 挂起当前协程直到 job 完成
    }

    println("主线程结束，耗时：$time ms")

    // GlobalScope 是一个顶级协程作用域,属于整个应用的生命周期
    // 协程在 GlobalScope 下启动后，不会自动绑定到任何生命周期（比如 Activity、Fragment 或自定义 CoroutineScope）。
    // 它的生命周期和 整个应用进程一样长，除非你手动取消 job.cancel()
    val job2 =GlobalScope.launch {
        delay(1000) // 挂起，不阻塞线程
        println("GlobalScope 协程完成")
    }

    println("主线程继续运行")
    // 阻塞主线程直到协程完成
    runBlocking { job2.join() }
}
