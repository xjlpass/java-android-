package com.example.androidlearn.okhttp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/*
*
* 1创建 OkHttpClient（全局单例最好）
2 构建 Request（url、请求方法、header、body）
3 newCall(request) 生成 Call 对象
4 call.enqueue () 异步 /execute () 同步
5 进入拦截器责任链
    重试拦截器
    桥接拦截器
    缓存拦截器
    连接拦截器
    网络拦截器
    服务端请求
        OkHttp 内部通过拦截器责任链完成网络请求，执行顺序：
        重试拦截器：处理请求失败重试、重定向；
        桥接拦截器：补充请求头、Cookie、编码等通用配置；
        缓存拦截器：读取 / 写入本地缓存，减少网络请求；
        连接拦截器：从连接池获取或创建 TCP 连接，复用 Socket；
        请求服务拦截器：组装 HTTP 报文，真正发起网络请求，接收服务端响应。
6 获取 Response 并回调
7 解析 body，切换主线程更新 UI
*
* */

class OkHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)

        // 1 创建OkHttpClient
        val client = OkHttpClient();

        // 2 构建request
        /*
        Request.Builder() → 建造器
        url() → 设置地址
        get() → 请求方式
        build() → 生成最终请求
        * */
        val request = Request.Builder()
            .url("https://www.baidu.com")
            .get()
            .build()

        // 创建 Call 对象
        /*
        * 关联客户端和请求
            生成一个可执行任务
            不执行请求，只是准备好
            真正执行要靠：
            call.execute() → 同步
            call.enqueue() → 异步
        *
        * */
        val call =client.newCall(request)

        // 异步执行请求
        call.enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error","请求失败")
            }

            override fun onResponse(call: Call, response: Response) {
                // 5 获取响应体
                val body = response.body?.string();
                Log.d("OkHttp","请求成功 $body")
            }
        })


    }
}