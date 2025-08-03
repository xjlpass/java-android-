package com.example.androidlearn.layoutparams

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// LayoutParams
class LayoutParamsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 创建LinearLayout 对象
        val linearLayout = LinearLayout(this)
        // 设置布局宽高
        linearLayout.layoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        // 设置排列方向（水平或垂直）
        linearLayout.orientation = LinearLayout.VERTICAL
        val textView = TextView(this)
        textView.setText("Layout Params")

        val textLinearLayout = LinearLayout.LayoutParams(200,200)

       linearLayout.addView(textView,textLinearLayout)
        setContentView(linearLayout)
    }
}