package com.example.androidlearn.interceptor

class LogInterceptor : BaseInterceptor() {
    override fun intercept(request: String) {
        println("日志拦截器：打印日志-》$request")

        nextInterceptor?.intercept(request)
    }
}