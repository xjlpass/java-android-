package com.example.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import androidx.appcompat.app.AppCompatActivity
import com.example.aidl.IExampleService

class ServiceActivity : AppCompatActivity() {

    private var exampleService: IExampleService? = null

    private val serviceConnection = object : android.content.ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            exampleService = IExampleService.Stub.asInterface(service)
            try {
                val result = exampleService?.add(5, 3)
                // Use result
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            exampleService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent("com.example.IExampleService")
        intent.setPackage("com.example")  // 确保目标包名
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }
}