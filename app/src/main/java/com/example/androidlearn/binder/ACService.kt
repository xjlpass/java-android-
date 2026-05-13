package com.example.androidlearn.binder

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.androidlearn.IACService

/*
* 服务端类 需要实现Stub*/

class ACService : Service() {

    // 把变量放在 Service 里，不要放在 Binder 里！
    private var mTemp = 22
    private var powerOn = false

    private val binder = object : IACService.Stub() {
        override fun setTemperature(temp: Int) {
            // 直接使用 Service 的变量，不要用 this！
            temperature = temp
            Log.d("ACService", "Temperature set to $temperature°C")
        }

        override fun getTemperature(): Int {
            return temperature
        }

        override fun turnOn() {
            powerOn = true
        }

        override fun turnOff() {
            powerOn = false
        }

        override fun isOn(): Boolean {
            return powerOn
        }
    }

    override fun onBind(intent: Intent?): IBinder? = binder
}