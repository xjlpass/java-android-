package com.example.androidlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

/* 学习android activity 生命周期
onNewIntent 方法是什么
    onNewIntent() 是 Activity 生命周期 里的回调方法。
    作用：当 Activity 已经存在栈中，再次被启动时，不会走 onCreate，只会触发 onNewIntent
触发场景
    只有满足 Activity 是复用旧实例 才会触发：
        启动模式为 singleTop
        启动模式为 singleTask
        启动模式为 singleInstance
        调用 startActivity 跳自己，页面不新建，复用已有实例
    //    不会走 onCreate、只会走 onNewIntent → onResume
复用旧 Activity（触发 onNewIntent）：
    onPause → onNewIntent → onResume
为什么要用 onNewIntent
    避免重复创建多个相同 Activity 实例
    接收新 Intent 携带的数据（跳转传参、通知跳转、Scheme 链接）
    刷新页面数据，不用重新走 onCreate

android 栈启动方式：
    standard：默认模式 每次启动都创建新的实例
    singleTop:新的activity在栈顶就复用，回调onNewIntent方法，
        如果存在不在栈顶，activity 会重新创建
        使用场景：浏览器的书签
    singleTask：栈内复用，检查整个栈内如果有那就复用，并回调onNewIntent方法；此模式启动 Activity A，
    系统首先会寻找是否存在 A 想要的任务栈，如果不存在，就会重新创建一个任务栈，然后把创建好 A 的实例放到栈中； 使用场景：某个Activity当做主界面的时候
        要启动的 Activity 已经在任务栈里存在
        直接把它上面所有的 Activity 全部弹出栈（销毁）
        把这个已存在的 Activity 直接挪到栈顶
        同时回调 onNewIntent()
    singleInstance：
        单实例模式，加强版singleTask，该Activity只能单独位于一个任务栈且该栈只有它一个
        使用场景：比如浏览器BrowserActivity很耗内存，很多app都会要调用它，这样就可以把该Activity设置成单例模式。比如：闹钟闹铃。
* */
class ActivityLearn : AppCompatActivity() {
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        // 必须把新 Intent 赋值给当前 Activity
        setIntent(intent)

        // 在这里解析新参数、刷新UI
        val data = intent?.getStringExtra("key")
    }
}