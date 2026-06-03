package com.example.androidlearn.sqlite.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    // autoGenerate = true 表示主键由数据库自动递增生成，
    // 插入数据时不需要手动指定 id，数据库会自动分配唯一值。默认值 0 只是占位，不会被使用。
    // Room 实体类的主键，推荐用 val（不可变），不要用 var
    /*
    * 1 为什么用 val？
            主键一旦插入，永远不能修改
            val = 只读，无法重新赋值
            符合数据库主键不可变的设计原则
            Room 官方示例全部使用 val
   2. 为什么不推荐 var？
            var 代表可修改
            但数据库主键不允许被更新
            用 var 会产生误导、不安全、容易写错*/
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int
)
