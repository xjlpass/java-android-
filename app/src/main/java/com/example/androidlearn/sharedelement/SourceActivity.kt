package com.example.androidlearn.sharedelement

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

class SourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source)

        val imageView = findViewById<ImageView>(R.id.sharedImageView)

        imageView.setOnClickListener {
            // 创建共享元素过渡动画
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                imageView,  // 共享元素
                "shared_image"  // 共享元素的名称，必须与目标活动中的元素名称一致
            )
            // 启动目标活动并传递过渡动画
            val intent = Intent(this, TargetActivity::class.java)
            startActivity(intent, options.toBundle())
        }
    }
}
