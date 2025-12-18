package com.example.lib.serializable

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

// Kotlin 中，data class 是 一种专门用来存储数据的类
// 注意：java和 kotlin 不能是相同的名字
data class User1(val name: String, val age: Int) : Serializable

fun main() {
    val user1 = User1("Alice", 123)

    try {
        // .dat 文件 = 数据文件，存放任意二进制或文本数据
        val out = ObjectOutputStream(FileOutputStream("user1.dat"))
        out.use { it.writeObject(user1) }
        println("序列化")
    } catch (e: Exception) {
        e.printStackTrace()
    }

    try {
        val in1 =ObjectInputStream(FileInputStream("user1.dat"))
        in1.use { it.readObject() as User1 }
        println("反序列化")
    }catch (e:Exception){
        e.printStackTrace()
    }
}