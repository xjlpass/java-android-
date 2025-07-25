package com.example.androidlearn.builder.Item

class Pepssi : ColdDrink() {
    override fun name(): String {
        return "Pepssi"
    }

    override fun price(): Float {
        return 35.0f
    }
}