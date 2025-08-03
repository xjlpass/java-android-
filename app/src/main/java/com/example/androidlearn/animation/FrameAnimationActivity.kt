package com.example.androidlearn.animation

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

// 逐帧动画
class FrameAnimationActivity : AppCompatActivity() {

    private var mFlag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_animation)
        val relativeLayout = findViewById<RelativeLayout>(R.id.rl)
        val btn = findViewById<Button>(R.id.startButton)
        val anim = relativeLayout.background as AnimationDrawable
        btn.setOnClickListener {
            if (mFlag) {
                anim.start()
            } else {
                anim.stop()
            }
            mFlag = !mFlag
        }
    }
}