package com.example.androidlearn.activity

/*
*
* Activity A 启动另一个 Activity B 会调用哪些方法？
如果B完全遮挡A ：
    A.onPause()
    B.onCreate()
    B.onStart()
    B.onResume()
    A.onStop()
没有完全遮住 B或者有透明区域
    A.onStop() 不会执行*/
class ActivityDemo {
}