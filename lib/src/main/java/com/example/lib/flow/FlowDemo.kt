package com.example.lib.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class FlowDemo{

}
suspend fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // 模拟耗时操作
        println("Emitting $i")
//        emit(i)   // 发送下一个值
    }
}

// ---------- main ----------
fun main() = runBlocking<Unit> {
    println("Starting example...")

    // 测量收集总耗时
    val time = measureTimeMillis {
        // 启动并发协程验证主线程未阻塞
        val job = launch {
            for (k in 1..3) {
                println("I'm not blocked $k")
                delay(100)
            }
        }

        // 收集 Flow
        simple().collect { value ->
            println("Collected $value")
        }

        job.join() // 等待并发协程结束
    }

    println("Done in $time ms")
}
