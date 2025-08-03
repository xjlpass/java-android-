package com.example.androidlearn.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

class PropertyAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ValueAnimator
//        val value = ValueAnimator.ofFloat(0f, 1f) as ValueAnimator
//        value.setDuration(2000)
//        value.addUpdateListener {
//            val nowValue = value.getAnimatedValue()
//            Log.e("PropertyAnimationActivity","nowValue=$nowValue",)
//        }
//        value.start()


        // ObjectAnimator
        setContentView(R.layout.property_animation)
        val textView = findViewById<TextView>(R.id.text)
        val objectAnimator = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f)
        objectAnimator.setDuration(4000)
        objectAnimator.start()
        // AnimatorListener 需要四个都写
        objectAnimator.addListener(object : Animator.AnimatorListener {
            // 动画开始时调用
            override fun onAnimationStart(animation: Animator) {
                // 可以在这里添加动画开始时的逻辑
                textView.text = "动画开始"
            }

            // 动画结束时调用
            override fun onAnimationEnd(animation: Animator) {
                // 可以在这里添加动画结束后的操作
                textView.text = "动画结束"
            }

            // 动画被取消时调用
            override fun onAnimationCancel(animation: Animator) {
                textView.text = "动画取消"
            }

            // 动画重复时调用（当前动画不会重复，所以此方法不会触发）
            override fun onAnimationRepeat(animation: Animator) {
                // 如果设置了repeatCount，这里会被调用
            }
        })
        // AnimatorListenerAdapter 可以只重写一个方法
        objectAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                textView.text = "动画结束"
            }
        })
    }
}