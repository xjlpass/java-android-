package com.example.androidlearn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearn.adapter.ButtonAdapter
import com.example.androidlearn.animation.FrameAnimationActivity
import com.example.androidlearn.animation.PropertyAnimationActivity
import com.example.androidlearn.animation.TweenedAnimationActivity
import com.example.androidlearn.databinding.Book
import com.example.androidlearn.databinding.DataBindingDemoBinding

class MainActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 下面代码与 DataBinding有关
//        val binding = DataBindingUtil.setContentView<DataBindingDemoBinding>(this, R.layout.data_binding_demo)

        // 创建 Book 对象并初始化
//        val book = Book()

        // 建立绑定关系
//        binding.book = book
//        book.name.set("平凡的世界")
//        book.author.set("路遥")

        // 下面数据与动画有关
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // 按钮文本
        val buttonList = listOf(
            "帧动画",
            "补间动画",
            "属性动画",
        )

        // 按钮与 Activity 的映射
        val activityMap = mapOf(
            "帧动画" to FrameAnimationActivity::class.java,
            "补间动画" to TweenedAnimationActivity::class.java,
            "属性动画" to PropertyAnimationActivity::class.java,
        )

        recyclerView.layoutManager = GridLayoutManager(this, 4)
        recyclerView.adapter = ButtonAdapter(buttonList) { buttonName ->
            activityMap[buttonName]?.let { targetActivity ->
                val intent = Intent(this, targetActivity)
                startActivity(intent)
            }
        }

    }
}