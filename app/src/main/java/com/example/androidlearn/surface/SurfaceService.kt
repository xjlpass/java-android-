package com.example.androidlearn.surface

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.view.Surface
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.androidlearn.ISurfaceService

class SurfaceService : Service() {

    private var player: ExoPlayer? = null

    private val binder = object : ISurfaceService.Stub() {
        override fun setSurface(surface: Surface?) {
            // TODO("Not yet implemented")
            Log.i("SurfaceService", "这里只是接收 Surface,实际渲染由对方 App 完成")
            surface?.let {
                Handler(Looper.getMainLooper()).post {
                    initPlayer(it)
                }
            }
        }

    }

    private fun initPlayer(it: Surface) {
        if (player == null) {
            player = ExoPlayer.Builder(this).build()
            val mediaItem = MediaItem.fromUri("https://vjs.zencdn.net/v/oceans.mp4")
            player?.setMediaItem(mediaItem)
            player?.setVideoSurface(it)
            player!!.prepare()
            player?.play()
        }
    }

    // 实现方法：作用是：onBind() 是 Service 生命周期中的一个关键方法，用于实现绑定服务（Bound Service）
    // intent: Intent?: 客户端绑定服务时传递的 Intent，包含绑定请求的相关信息
    // 返回值 IBinder?: 返回一个 IBinder 接口，客户端通过这个接口与服务进行通信
    override fun onBind(intent: Intent): IBinder {
        // 如果下面代码未注释，后面的日志打印和return 不会执行
        // TODO("Not yet implemented")
        Log.i("SurfaceService", "onBind")
        return binder
    }


}