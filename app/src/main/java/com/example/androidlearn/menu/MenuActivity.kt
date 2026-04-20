package com.example.androidlearn.menu

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.androidlearn.R

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // 替换为你的布局文件名
    }

    // 加载菜单布局（对应你之前写的menu xml文件）
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // 初始化MenuInflater，加载菜单资源
        val inflater: MenuInflater = menuInflater
        // 这里的R.menu.xxx 需要替换成你实际的menu xml文件名（比如menu_main）
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    // 处理菜单点击事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_save -> {
                // 编写保存的业务逻辑
                true // 表示事件已处理
            }
            R.id.menu_item_delete -> {
                // 编写删除的业务逻辑
                true // 表示事件已处理
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}