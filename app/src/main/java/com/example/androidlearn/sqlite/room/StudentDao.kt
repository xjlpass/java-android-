package com.example.androidlearn.sqlite.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// 定义 DAO（数据操作接口）
// Room 是现代 Android 项目操作数据库的首选方案，比 SQLiteOpenHelper 更安全、更简洁、支持 Kotlin 协程。
@Dao
interface StudentDao {

    @Insert
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("SELECT * FROM student")
    suspend fun getAll(): List<Student>

    // suspend 支持协程异步操作，避免阻塞 UI
    @Query("SELECT * FROM student WHERE id = :id")
    suspend fun getById(id: Int): Student?

    // LiveData/Flow
    @Query("SELECT * FROM student")
    fun getAllFlow(): Flow<List<Student>>
}
