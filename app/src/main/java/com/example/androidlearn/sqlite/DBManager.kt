package com.example.androidlearn.sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object DBManager {

    private const val DB_NAME = "student.db"

    /**
     * 把 assets 中的 student.db 复制到应用内部数据库目录
     */
    fun copyDatabase(context: Context) {
        // assets/student.db 是打包进 APK 的资源文件，不能直接写入
        val dbFile: File = context.getDatabasePath(DB_NAME)

        // 如果数据库已经存在，就不重复复制
        if (dbFile.exists()) {
            return
        }

        // 创建 databases 目录
        val dbDir = dbFile.parentFile
        if (dbDir != null && !dbDir.exists()) {
            dbDir.mkdirs()
        }

        try {
            context.assets.open(DB_NAME).use { inputStream ->
                FileOutputStream(dbFile).use { outputStream ->
                    val buffer = ByteArray(1024)
                    var length: Int

                    while (inputStream.read(buffer).also { length = it } > 0) {
                        outputStream.write(buffer, 0, length)
                    }

                    outputStream.flush()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 打开数据库
     */
    fun openDatabase(context: Context): SQLiteDatabase {
        val dbFile = context.getDatabasePath(DB_NAME)

        return SQLiteDatabase.openDatabase(
            dbFile.path,
            null,
            SQLiteDatabase.OPEN_READWRITE
        )
    }

    /**
     * 查询 student 表中的所有数据
     */
    fun queryAllStudents(context: Context): List<Student> {
        val studentList = mutableListOf<Student>()
        val db = openDatabase(context)

        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery("SELECT * FROM student", null)

            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow("age"))

                val student = Student(id, name, age)
                studentList.add(student)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
            db.close()
        }

        return studentList
    }

    /**
     * 插入一条学生数据
     */
    fun insertStudent(context: Context, name: String, age: Int) {
        val db = openDatabase(context)

        try {
            val sql = "INSERT INTO student(name, age) VALUES(?, ?)"
            db.execSQL(sql, arrayOf(name, age))
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    /**
     * 根据 id 删除学生
     */
    fun deleteStudentById(context: Context, id: Int) {
        val db = openDatabase(context)

        try {
            db.execSQL("DELETE FROM student WHERE id = ?", arrayOf(id))
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    /**
     * 根据 id 修改学生信息
     */
    fun updateStudent(context: Context, id: Int, name: String, age: Int) {
        val db = openDatabase(context)

        try {
            val sql = "UPDATE student SET name = ?, age = ? WHERE id = ?"
            db.execSQL(sql, arrayOf(name, age, id))
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }
}
