package com.example.androidlearn.decorator

import android.util.Log
import android.widget.Button

/**
 * 具体组件：包装原生Button
 */
class NormalButton(val button: Button) : ClickableView {
    // 新增：存储原生点击回调（避免丢失业务逻辑）
    private var originalClickListener: (() -> Unit)? = null

    // 提供方法设置原生点击逻辑
    fun setOriginalClickListener(listener: () -> Unit) {
        this.originalClickListener = listener
    }

    override fun performClick() {
        Log.d("DecoratorDemo", "1. 执行原生点击逻辑")
        // 直接执行原生点击逻辑，而非调用button.performClick()（避免触发监听递归）
        originalClickListener?.invoke()
    }
}