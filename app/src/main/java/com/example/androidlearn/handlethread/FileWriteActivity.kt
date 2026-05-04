package com.example.androidlearn.handlethread

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

class FileWriteActivity : AppCompatActivity() {
    // persistentState:用于保存“跨进程死亡后仍然保留的数据”
    // 注意这个不会主动执行
    // APP 被系统意外杀死 → 重启恢复时，并且需要恢复「持久化状态」时
    // 平时正常启动、正常打开、正常退出、正常重建都绝对不会走这里
    // override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    //     super.onCreate(savedInstanceState, persistentState)
    //     setContentView(R.layout.activity_handler_thread)
    //
    //     Log.i("FileWriteActivity","123")
    //     // 多次写入，自动排队串行执行
    //     FileWriteManager.instance.writeLog("App启动成功")
    //     FileWriteManager.instance.writeLog("用户进入首页")
    //     FileWriteManager.instance.writeLog("接口请求完成")
    // }
    // ✅ 正确的 onCreate（只有 2 个参数）
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_thread)

        Log.i("FileWriteActivity", "123")  // 现在一定能打印

        FileWriteManager.instance.writeLog("App启动成功")
        FileWriteManager.instance.writeLog("用户进入首页")
        FileWriteManager.instance.writeLog("接口请求完成")
    }


    override fun onDestroy() {
        super.onDestroy()
        // 销毁释放 避免内存泄露
        FileWriteManager.instance.release()
    }
}