package com.example.androidlearn.interceptor


/*
* 抽象处理者：责任链基类
* 责任链模式的 4 大组成部分
抽象处理者（Handler）
  定义处理请求的接口
  持有下一个处理者的引用
  是所有处理器的父类 / 接口
具体处理者（ConcreteHandler）
  真正处理请求的类
  判断自己能不能处理
  能处理就处理，不能就传给下一个
请求对象（Request）
  要被传递、处理的数据
  比如请求参数、事件、消息等
责任链（Chain）
  把多个 ConcreteHandler 按顺序串起来
  客户端从第一个节点发起请求
* */

/*问题一：抽象处理者必须定义成接口类型吗？
            抽象处理者不一定是接口，抽象类、接口都可以；
            责任链需要持有下一个处理者的引用，所以通常用抽象类；
            接口只用来定义行为，不适合直接作为完整的抽象处理者
* */

abstract class BaseInterceptor {
    // 下一个处理器
    var nextInterceptor : BaseInterceptor? = null

    // 处理下一个请求
    abstract fun intercept(request: String)
}