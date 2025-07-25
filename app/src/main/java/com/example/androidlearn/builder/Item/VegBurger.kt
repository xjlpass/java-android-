package com.example.androidlearn.builder.Item

// 植物汉堡
class VegBurger : Burger(){
    override fun name(): String {
        return "Veg Burger"
    }

    override fun price(): Float {
        return 25.0f
    }
}