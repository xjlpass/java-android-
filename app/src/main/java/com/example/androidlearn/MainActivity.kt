package com.example.androidlearn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidlearn.databinding.Book
import com.example.androidlearn.databinding.DataBindingDemoBinding

class MainActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<DataBindingDemoBinding>(this, R.layout.data_binding_demo)

        // 创建 Book 对象并初始化
        val book = Book()

        // 建立绑定关系
        binding.book = book
        book.name.set("平凡的世界")
        book.author.set("路遥")


        // 修改数据，无需调用 setText，UI 就能更新

    }
}