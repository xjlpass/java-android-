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
        A.onStop() 不会执行
 onSaveInstanceState 的作用
     *  onSaveInstanceState() 与 onPause() 的先后顺序不保证
     *  onSaveInstanceState 调用在 onStop 之前
     * onRestoreInstanceState() 在 onStart() 后。
FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS  标识位
*       作用：这个页面退出后，不在手机「最近任务列表」里显示
*       场景用途
            登录页、闪屏页、弹窗临时页面
            不想让用户从最近任务再次打开这个界面
            退出即彻底隐藏，不留后台记录
 横竖屏切换的 Activity 生命周期变化?
     * 默认情况下，系统会销毁并重建 Activity，以便重新加载不同方向下的资源文件，因此生命周期会执行：
     * onPause → onStop → onDestroy→ onCreate → onStart → onResume
     *如果在 Manifest 中配置：
     * android:configChanges="orientation|screenSize"
     * 则表示由应用自行处理配置变化，系统不会重建 Activity，而是回调：onConfigurationChanged()
     *
     *
   Intent 内部有一个 Bundle 类型成员变量 mExtras，
        调用 putExtra() 时，本质是向 mExtras 中存储键值对数据；
        调用 getExtra() 时，则是从 mExtras 中读取数据。
        因此 Intent 实际上是通过 Bundle 来实现数据传递的

 android 进程机制
 * Android 通过 lmkd 与 oom_adj 管理内存，按「前台 > 可视 > 服务 > 后台 > 空进程」优先级保护；
 * 内存不足从空进程开始回收，同优先级下占用内存更大、更久未使用的进程优先杀死。
 *
 *
 *
 * 安卓进程常见保活方案
        保活方式	                场景	   优点	    注意事项
        前台 Service	            导航、音乐、实时监控	高优先级，稳定	必须显示通知或车机特权通知
        JobScheduler / WorkManager	周期同步、日志上传	官方推荐、节能	不保证实时执行
        系统广播 / 事件	        点火、熄火、网络变化	可事件触发	高版本限制，需显式注册
        推送唤醒	                OTA、远程指令	可远程唤醒	依赖车联网平台或厂商推送
        白名单 / 系统特权	        所有关键后台	系统不会杀掉	需要系统签名或 OEM 支持
        硬件绑定	                点火/熄火/车辆事件	节能、安全	非应用可控，需 OEM 支持
        *
       不推荐：双进程守护、一像素 Activity
        *

    * */
class ActivityDemo {
}