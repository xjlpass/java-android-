package com.example.androidlearn.animation

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

// 补间动画
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