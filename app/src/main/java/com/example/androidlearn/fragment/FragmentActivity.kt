package com.example.androidlearn.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R
import android.widget.Button

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment)
        replaceFragment(RightFragment())
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            replaceFragment(AnthorRightFragment())
        }
        replaceFragment(RightFragment())
    }
    private fun replaceFragment(fragment: Fragment){
        // Fragment 管理器，负责管理 Activity 里的 Fragment 生命周期、事务（添加 / 替换 / 移除等）
        val fragmentManager =supportFragmentManager
        //开启一个 Fragment 事务（Transaction），事务是一组 Fragment 操作的集合（如替换、添加），需通过事务提交生效
        val transaction =fragmentManager.beginTransaction()
        transaction.replace(R.id.rightFrag,fragment)
        transaction.commit()
    }
}
