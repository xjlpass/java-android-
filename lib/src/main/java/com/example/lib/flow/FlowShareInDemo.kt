package com.example.lib.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    // 上游冷流，每秒发一个数字
    val coldFlow = flow {
        for (i in 1..5) {
            println("emit $i")
            emit(i)
            kotlinx.coroutines.delay(1000)
        }
    }

    /*
    * viewModelScope	ViewModel	ViewModel 生命周期	网络请求、Flow 转换、业务逻辑协程
      lifecycleScope	Fragment/Activity	UI 生命周期	收集 Flow、UI 更新、动画、延迟操作等
    * */
    // 转成热流（sharedFlow）
    val sharedFlow =
        coldFlow.shareIn(
            scope = this,  // 使用 runBlocking 的作用域，实际 UI 可以用 viewModelScope
            replay = 1, // 新订阅者立刻收到最近一条数据
            started = SharingStarted.WhileSubscribed(2000) // 有订阅才启动，上次取消后延迟 2 秒停止
        )

    println("=== 第一个订阅者开始 ===")
    val job1 = launch {
        sharedFlow.collect { value ->
            println("Collector1 received: $value")
        }
    }

    delay(2500) // 等 2.5 秒再启动第二个订阅者

    println("=== 第二个订阅者开始 ===")
    val job2 = launch {
        sharedFlow.collect { value ->
            println("Collector2 received: $value")
        }
    }

    delay(4000) // 等一会儿观察 SharedFlow 的行为
    println("=== 第一个订阅者取消 ===")
    job1.cancel()

    delay(3000) // 等一会儿
    println("=== 第二个订阅者取消 ===")
    job2.cancel()

    delay(3000) // 再等 3 秒，观察上游是否停止
    println("=== 演示结束 ===")
}