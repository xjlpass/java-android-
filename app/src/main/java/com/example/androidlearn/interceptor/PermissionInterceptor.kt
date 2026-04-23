package com.example.androidlearn.interceptor

class PermissionInterceptor : BaseInterceptor() {
    override fun intercept(request: String) {
        println("权限拦截器：检查权限")

        // 模拟
        if(request.contains("login")){
            println("权限校验通过")
            nextInterceptor?.intercept(request)
        }else{
            println("权限校验不通过，终止")
        }
    }
}