package com.example.androidlearn.builder.Item

class MealBuilder {

    fun prepareVegMeal(): Meal {
        val meal = Meal()
        meal.addItem(VegBurger())
        meal.addItem(Coke())
        return meal
    }

    fun prepareNonVegMeal(): Meal {
        val meal = Meal()
        meal.addItem(ChickenBurger())
        meal.addItem(Pepssi())
        return meal
    }
}
