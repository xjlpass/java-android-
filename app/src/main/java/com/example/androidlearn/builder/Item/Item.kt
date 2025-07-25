package com.example.androidlearn.builder.Item

import com.example.androidlearn.builder.packing.Packing

// 食物条目
interface Item {
    fun name(): String
    fun packing(): Packing
    fun price(): Float
}