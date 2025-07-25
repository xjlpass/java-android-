package com.example.androidlearn.sharedelement

import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

class TargetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)

        // 获取共享元素并设置进入过渡动画
        val imageView = findViewById<ImageView>(R.id.sharedImageView)
        imageView.transitionName = "shared_image"  // 设置共享元素的名称，必须与源活动中的名称一致

        // 设置共享元素过渡动画
        val transition: Transition = TransitionInflater.from(this)
            .inflateTransition(R.transition.shared_element_transition)

        window.sharedElementEnterTransition = transition
        window.sharedElementEnterTransition = transition
    }
}
