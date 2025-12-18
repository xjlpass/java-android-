package com.example.androidlearn.surface

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.ISurfaceService

class SurfaceActivity : AppCompatActivity() {
    private var surfaceService: ISurfaceService? = null

    //  lateinit  延迟初始化
    //       只能用于可变变量（var）
    //       只能用于非空引用类型：不能用于基本数据类型（如 Int、Boolean），也不能用于可空类型（如 SurfaceView?）
    //      必须在首次使用前赋值：如果没赋值就直接使用，会抛出 UninitializedPropertyAccessException 异常
    private lateinit var surfaceView: SurfaceView


    //ServiceConnection 是 Android 提供的一个接口，它的核心作用是：作为Activity/Fragment（客户端） 和 绑定式 Service（服务端） 之间的 “通信桥梁”，
    // 用于监听客户端与服务的连接状态，并获取服务的实例，从而实现客户端对服务的直接调用。
    private val connection = object : ServiceConnection {
        // 	连接成功回调
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // 把原始 IBinder 转换成可调用的 AIDL 接口实例；
            surfaceService = ISurfaceService.Stub.asInterface(service)
            // 把 SurfaceView 的底层画板传给服务端，实现 “服务端渲染、客户端显示” 的效果；
            surfaceView.holder.surface.let { surface ->
                surfaceService?.setSurface(surface)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            surfaceService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        surfaceView = SurfaceView(this)
        surfaceView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setContentView(surfaceView)

        //  帮顶服务端Service
        val intent = Intent("com.example.surface.SURFACE_SERVICE")
        intent.setPackage("com.example.androidlearn")
        bindService(intent,connection, Context.BIND_AUTO_CREATE)

        surfaceView.holder.addCallback(object :SurfaceHolder.Callback{
            override fun surfaceCreated(holder: SurfaceHolder) {
                surfaceService?.setSurface(holder.surface)
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

}