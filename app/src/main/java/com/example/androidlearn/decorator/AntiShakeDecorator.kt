package com.example.androidlearn.decorator

import android.util.Log

/**
 * 防抖装饰器：500ms防重复点击
 */
class AntiShakeDecorator(decoratedView: ClickableView) : ViewDecorator(decoratedView) {
    private var lastClickTime: Long = 0
    private val INTERVAL = 500L

    override fun performClick() {
        val currentTime = System.currentTimeMillis()
        Log.d("DecoratorDemo", "2. 执行防抖判断逻辑")

        if (currentTime - lastClickTime > INTERVAL) {
            super.performClick()
            lastClickTime = currentTime
        } else {
            Log.d("DecoratorDemo", "→ 点击太频繁，忽略本次点击")
        }
    }
}