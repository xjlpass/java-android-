package com.example.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder

class ExampleService : Service() {

    // 这是你需要实现的 Binder 接口
    private val binder = object : IExampleService.Stub() {
        override fun add(a: Int, b: Int): Int {
            return a + b
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder  // 返回正确的 IBinder 实现
    }
}