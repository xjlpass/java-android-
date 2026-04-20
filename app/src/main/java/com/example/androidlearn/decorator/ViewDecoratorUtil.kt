package com.example.androidlearn.decorator

/**
 * 装饰器工具类：单例封装，统一组合装饰器逻辑
 */
object ViewDecoratorUtil {
    // 入参改为NormalButton，而非直接传Button（便于绑定原生点击逻辑）
    fun getDecoratedButton(normalButton: NormalButton): ClickableView {
        return normalButton
            .let { AntiShakeDecorator(it) }
            .let { RoundedCornerDecorator(it) }
    }

    fun getAntiShakeButton(normalButton: NormalButton): ClickableView {
        return normalButton.let { AntiShakeDecorator(it) }
    }

    fun getRoundedCornerButton(normalButton: NormalButton): ClickableView {
        return normalButton.let { RoundedCornerDecorator(it) }
    }
}