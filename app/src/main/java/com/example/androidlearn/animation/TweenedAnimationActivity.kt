package com.example.androidlearn.animation

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

// 补间动画
/*
*  动画包括 帧动画，补间动画，属性动画
*
*  补间动画和属性动画对比：
* 动画类型	视觉效果	真正属性是否改变	影响点击区域
    View 动画	看起来移动了	❌ 不变	❌ 不变
    属性动画	看起来移动了	✅ 改变	✅ 改变
* */
class TweenedAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweened_animation)

        val img = findViewById<ImageView>(R.id.img)
        img.setOnClickListener {
            // 透明度动画
//            val animation = AnimationUtils.loadAnimation(this, R.anim.alpha)
//            img.startAnimation(animation)
            // 旋转动画
//            val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
//            img.startAnimation(animation)
            // 缩放动画
//            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
//            img.startAnimation(animation)
            // 平移
            val animation = AnimationUtils.loadAnimation(this, R.anim.translate)
            img.startAnimation(animation)
        }

    }
}