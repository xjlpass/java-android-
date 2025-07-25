package com.example.androidlearn.builder.Item

import com.example.androidlearn.builder.packing.Packing
import com.example.androidlearn.builder.packing.Wrapper

// 汉堡
abstract class Burger : Item {
    override fun packing(): Packing {
        return Wrapper()
    }
}