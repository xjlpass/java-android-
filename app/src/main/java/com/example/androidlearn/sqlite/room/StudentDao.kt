package com.example.androidlearn.sqlite.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// 定义 DAO（数据操作接口）
// Room 是现代 Android 项目操作数据库的首选方案，比 SQLiteOpenHelper 更安全、更简洁、支持 Kotlin 协程。
@Dao
interface StudentDao {

    // 查询全部学生，Flow 可以自动监听数据库变化
    @Query("SELECT * FROM student ORDER BY id DESC")
    fun getAllStudents(): Flow<List<Student>>

    // 根据 id 查询单个学生
    @Query("SELECT * FROM student WHERE id = :id")
    suspend fun getStudentById(id: Int): Student?

    // 插入学生
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    // 批量插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudents(students: List<Student>)

    // 修改学生
    @Update
    suspend fun updateStudent(student: Student)

    // 删除学生
    @Delete
    suspend fun deleteStudent(student: Student)

    // 根据 id 删除
    @Query("DELETE FROM student WHERE id = :id")
    suspend fun deleteStudentById(id: Int)

    // 删除全部
    @Query("DELETE FROM student")
    suspend fun deleteAllStudents()
}
