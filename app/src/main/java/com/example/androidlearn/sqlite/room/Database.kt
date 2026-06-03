package com.example.androidlearn.sqlite.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

// 定义 Database（数据库管理类）
// 使用 Room.databaseBuilder() 创建数据库
//
// @Volatile + synchronized 保证单例
@Database(entities = [Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "student.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
