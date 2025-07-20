package com.example.androidlearn.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

// 子线程通过handler 向主线程发送信息
class HandlerActivity : AppCompatActivity() {
    private val handler =object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg:Message){
            val result =msg.obj as String
            val textView :TextView =findViewById(R.id.textView)
            textView.text = result
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.handler)

        // 模拟子线程在执行任务
        Thread(Runnable{
            try {
                Thread.sleep(3000)
            //在 Java 中，InterruptedException是一个受检查异常（Checked Exception），
            // 它在线程被中断时抛出。当一个线程处于等待（如wait()）、** 休眠（如Thread.sleep()）或阻塞（如join()）** 状态时，如果其他线程调用了该线程的interrupt()方法，就会触发InterruptedException。
            }catch (e:InterruptedException){
                e.printStackTrace()
            }
            // Message.obtain() 是 Android 中 Message 类的一个 静态工厂方法，用于从消息池中获取一个可复用的 Message 实例
            // message.obj 是 Message 类中的一个字段，通常用于存储与消息相关的数据。这个字段是 Object 类型，因此可以存储任何类型的数据，包括基本数据类型的包装类、字符串、数组、自定义对象等
            val message =Message.obtain()
            message.obj ="数据下载完成"
            // 将消息发送到Handler中
            handler.sendMessage(message)
        }).start()
    }
}