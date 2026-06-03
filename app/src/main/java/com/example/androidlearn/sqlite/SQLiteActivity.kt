package com.example.androidlearn.sqlite

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SQLiteActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvResult = TextView(this)
        tvResult.textSize = 18f
        setContentView(tvResult)

        // 第一步：复制 assets 中的数据库到内部数据库目录
        DBManager.copyDatabase(this)

        // 第二步：查询数据库
        val students = DBManager.queryAllStudents(this)

        // 第三步：显示查询结果
        val result = StringBuilder()
        result.append("学生信息：\n\n")

        for (student in students) {
            result.append("ID：${student.id}\n")
            result.append("姓名：${student.name}\n")
            result.append("年龄：${student.age}\n")
            result.append("------------------\n")
        }

        tvResult.text = result.toString()

        // 下面是增删改示例，需要时再打开使用

        // 插入数据
        // DBManager.insertStudent(this, "赵六", 23)

        // 修改数据
        // DBManager.updateStudent(this, 1, "张三修改后", 25)

        // 删除数据
        // DBManager.deleteStudentById(this, 2)
    }
}