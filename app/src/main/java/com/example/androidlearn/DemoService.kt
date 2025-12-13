package com.example.androidlearn

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.androidlearn.channel.NotificationUtil

class DemoService : Service() {

    override fun onCreate() {
        super.onCreate()
        // 1️⃣ 先创建 NotificationChannel
        NotificationUtil.createChannel(this)
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // 2️⃣ 检查 Android 13+ 通知权限
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Log.w("DemoService", "POST_NOTIFICATIONS not granted, using minimal notification")
            // 权限未授予时用最简单通知，保证 startForeground 不崩
            NotificationCompat.Builder(this, NotificationUtil.CHANNEL_ID)
                .setSmallIcon(R.drawable.b)
                .setContentTitle("服务运行中")
                .setContentText("权限未授予，通知可能不显示")
                .build()
        } else {
            // 权限已授予，正常通知
            NotificationCompat.Builder(this, NotificationUtil.CHANNEL_ID)
                .setSmallIcon(R.drawable.b)
                .setContentTitle("前台 Service")
                .setContentText("Service 正在运行")
                .build()
        }

        // 3️⃣ 启动前台服务
        startForeground(1, notification)

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}

