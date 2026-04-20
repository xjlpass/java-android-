package com.example.androidlearn.decorator

import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.androidlearn.R

/**
 * 圆角装饰器：设置Button圆角背景
 */
class RoundedCornerDecorator(decoratedView: ClickableView) : ViewDecorator(decoratedView) {
    override fun performClick() {
        Log.d("DecoratorDemo", "3. 执行圆角设置逻辑")

        // Kotlin 智能类型判断 + 空安全
        (decoratedView as? NormalButton)?.button?.apply {
            val roundBg = ContextCompat.getDrawable(context, R.drawable.round_corner_bg)
            background = roundBg
        }

        super.performClick()
    }
}