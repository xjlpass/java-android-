package com.example.androidlearn.interceptor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R


class InterceptorActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chain)

        // 执行责任链
        val p = PermissionInterceptor()
        val l = LogInterceptor()
        val r = NetWorkInterceptor()

        p.nextInterceptor = l
        l.nextInterceptor = r

        p.intercept("login requeset")
    }
}