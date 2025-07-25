package com.example.androidlearn.builder.Item

import com.example.androidlearn.builder.packing.Bottle
import com.example.androidlearn.builder.packing.Packing

abstract class ColdDrink: Item{
    override fun packing(): Packing {
        return Bottle()
    }
}