package com.example.androidlearn.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R
import java.util.concurrent.CountDownLatch

class HandlerActivityB : AppCompatActivity() {
    // 在 Kotlin 中，lateinit 是一个用于处理非空属性的修饰符，允许你声明一个非空类型的属性，但延迟初始化它的值。
    // 这在某些无法在对象创建时立即初始化属性的场景中非常有用。
    private lateinit var mChildHandler: Handler
    private lateinit var mLatch: CountDownLatch

    // 在 Android 开发中，Bundle 是一个重要的类，用于在组件（如 Activity、Service、Fragment）之间传递数据。\
    // 它内部使用键值对（Key-Value）存储数据，支持基本数据类型（如 int、String）和可序列化对象
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.handler_2)

        mLatch = CountDownLatch(1)

        // 创建子线程
        val childThread = Thread(Runnable {
            // 子线程创建Handler，绑定子线程Looper
            // 初始化 Handler
            // Looper.myLooper() 是一个用于获取当前线程的 Looper 实例的方法
           Looper.prepare()

            mChildHandler = object : Handler(Looper.myLooper()!!) {
                override fun handleMessage(msg: Message) {
                    // 子线程收到信息后处理
                    val messageContent = msg.obj as String
                    // 子线程处理信号后执行某个操作
                    // runOnUiThread() 让你能在子线程中执行完任务后，回到主线程更新 UI
                    runOnUiThread {
                        val textView: TextView = findViewById(R.id.textView)
                        textView.text = messageContent
                    }
                }

            }
            // 通知主线程，子线程 Handler 已经初始化完成
            mLatch.countDown()

            // 模拟子线程任务
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            // 子线程创建后，通过Handler接收主线程发送的消息
            // 启动消息循环
            Looper.loop()
        })

        childThread.start()

        // 等待子线程的 Handler 初始化完成
        mLatch.await()

        // 主线程发送信息给子线程
        val mainHandler = Handler(mainLooper)
        val message = Message.obtain()
        message.obj = "This is a message from the main thread"
        mChildHandler.sendMessage(message)
    }
}