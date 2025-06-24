package com.example.androidlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.example.androidlearn.databinding.DataBindingDemoBinding
import com.example.androidlearn.databingding.User


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 创建数据模型实例
        val user = User("John Doe", 30)

        // 使用 DataBinding 来绑定视图和数据
        val binding: DataBindingDemoBinding = DataBindingUtil.setContentView(this, R.layout.data_binding_demo)

        // 设置数据到绑定对象
        binding.user = user
    }
}