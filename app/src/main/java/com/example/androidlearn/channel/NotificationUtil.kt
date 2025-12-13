package com.example.androidlearn.channel

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.androidlearn.R

object NotificationUtil {

    const val CHANNEL_ID = "demo_channel_id"
    const val CHANNEL_NAME = "Demo Channel"

    fun createChannel(context: Context) {
        // Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            // 普通优先级的通知
            /*
            * IMPORTANCE_HIGH 弹窗打断用户
            * IMPORTANCE_DEFAULT 正常通知（最常用）
            * IMPORTANCE_LOW 静默显示
            * IMPORTANCE_MIN 基本不可见
            * IMPORTANCE_NONE 完全屏蔽
            * */
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "这是一个演示用的通知 Channel"
        }

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    @RequiresPermission(android.Manifest.permission.POST_NOTIFICATIONS)
    fun sendNotification(context: Context, s: String, s1: String) {
        // 创建channel
        createChannel(context)

        // 构建通知
        val notification =
            NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(
                R.drawable.b
            ).setContentTitle("通知标题").setContentText("这是一条测试通知").setAutoCancel(true)
                .build()
        notifySafely(context, notification)

    }

    @RequiresPermission(android.Manifest.permission.POST_NOTIFICATIONS)
    fun notifySafely(context: Context, notification: Notification) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!granted) {
                // ⚠️ 不能直接 notify
                Log.w("Notify", "POST_NOTIFICATIONS not granted")
                return
            }
        }

        NotificationManagerCompat.from(context).notify(1, notification)
    }

}