package com.example.androidlearn.scheme

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

class SchemeActivity : AppCompatActivity() {

    private lateinit var tvInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheme)
        tvInfo = findViewById(R.id.tv_info)

        // 第一次拉起解析参数
        parseSchemeIntent(intent)
    }

    // 复用Activity时回调 关键方法
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        // 再次解析新的Scheme参数
        parseSchemeIntent(intent)
    }

    // 解析Scheme参数统一方法
    private fun parseSchemeIntent(intent: Intent?) {
        val uri: Uri? = intent?.data
        uri?.let {
            val name = it.getQueryParameter("name")
            val age = it.getQueryParameter("age")
            tvInfo.text = "收到Scheme参数：\n姓名：$name\n年龄：$age"
        }
    }
}