package com.example.androidlearn.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/*、
* SQLiteDatabase 对象中核心方法
        * insert()       // 插入数据
        delete()       // 删除数据
        update()       // 修改数据
        query()        // 查询数据
        rawQuery()     // 执行查询 SQL
        execSQL()      // 执行无返回结果的 SQL
   ContentValues 用来封装插入或修改的数据，类似键值对
   * Room
        Room 是 Google 官方推荐的数据库框架，是对 SQLite 的封装
        * Room包括
        *       Entity：实体类，对应数据库表
                Dao：数据库操作接口
                Database：数据库管理类
                *

    * 如何导入外部数据库
    *       Android 中不能直接操作 assets 目录下的数据库文件，因为 assets 里的文件是只读的
    *       正确做法是：
    *                   1. 把 .db 文件放到 assets 目录
                        2. App 第一次启动时，把 .db 文件复制到手机内部数据库目录
                        3. 使用 SQLiteDatabase.openDatabase() 打开数据库
                        4. 使用 SQL 查询或修改数据
             数据库复制后的路径一般是：
                /data/data/你的包名/databases/student.db
             在 Kotlin 里可以通过下面方式获取：
                context.getDatabasePath("student.db")

   *
*/
class SQLiteDemo( context: Context) : SQLiteOpenHelper(context, "student.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented1")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented1")
    }
}