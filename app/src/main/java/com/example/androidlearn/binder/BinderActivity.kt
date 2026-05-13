package com.example.androidlearn.binder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.IACService

/*
* Binder是什么？
    * Binder 是 Android 系统中非常核心的 进程间通信（IPC, Inter-Process Communication）机制，
    * 本质上是 Android 用来让不同进程安全、高效地“说话”的桥梁
    * 不同进程在内存中属于不同的虚拟空间，虚拟空间包括用户空间（数据不共享）和内核空间（数据共享）
*Binder 的组成
    客户端（Client）：发起请求的进程。
    服务端（Service）：提供服务的进程。
    Stub / Proxy：
    Stub：服务端的接口实现，收到 Binder 请求后调用真正的方法。
    Proxy：客户端调用的代理对象，把方法调用打包，通过 Binder 传给 Stub。
    Binder 驱动：内核模块，负责跨进程传输数据
* */
class BinderActivity : AppCompatActivity() {

    // acService 用来保存远程 ACService 的 Binder 接口实例
    // 这个对象本质上是 Proxy，调用它的方法会通过 Binder 发送给远程进程的 Stub
    private var acService: IACService? = null

    // 管理 客户端与远程 Service 的绑定状态
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            acService = IACService.Stub.asInterface(service)
            Log.d("CarHUD", "Connected to ACService")

            try {
                acService?.turnOn()
                acService?.setTemperature(24)
                val currentTemp = acService?.getTemperature()
                Log.d("CarHUD", "Current Temperature: $currentTemp")
            } catch (e: Exception) {
                Log.e("CarHUD", "Binder call failed", e)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            acService = null
            Log.d("CarHUD", "Disconnected from ACService")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 安全绑定远程 Service
        val intent = Intent(this, ACService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}