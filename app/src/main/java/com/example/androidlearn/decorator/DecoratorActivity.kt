package com.example.androidlearn.decorator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

class DecoratorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decorative_button)

        // 1. 获取原生Button
        val decorativeButton: Button = findViewById(R.id.btn_decorative_test)

        // 2. 初始化基础组件，并设置原生点击逻辑
        val normalButton = NormalButton(decorativeButton)
        // 这里设置原生点击的业务逻辑（替代原来的setOnClickListener）
        normalButton.setOriginalClickListener {
            Log.d("DecoratorDemo", "→ 原生Button点击事件触发！")
        }

        // 3. 组合装饰器
        val decoratedButton = ViewDecoratorUtil.getDecoratedButton(normalButton)

        // 4. 给原生Button设置唯一的点击监听：直接调用装饰器的performClick()
        decorativeButton.setOnClickListener {
            decoratedButton.performClick() // 此时不会递归，因为NormalButton不再调用button.performClick()
        }
    }
}