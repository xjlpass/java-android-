package com.example.androidlearn.interceptor

class NetWorkInterceptor: BaseInterceptor() {
    override fun intercept(request: String) {
        println("网络执行器：真正发送网络请求$request")
        println("请求结束")
    }
}