package com.example.androidlearn.builder.Item

class Coke : ColdDrink(){
    override fun name(): String {
        return "Coke"
    }

    override fun price(): Float {
        return 30.0f
    }
}