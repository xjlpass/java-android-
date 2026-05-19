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
    * */
class ActivityDemo {
}