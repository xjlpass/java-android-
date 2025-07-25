package com.example.androidlearn.builder.Item

class BuilderPatternDemo {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val mealBuilder = MealBuilder()

            // 创建素食餐
            val vegMeal = mealBuilder.prepareVegMeal()
            println("Veg Meal")
            vegMeal.showItems()
            println("Total Cost: ${vegMeal.getCost()}")

            // 创建非素食餐
            val nonVegMeal = mealBuilder.prepareNonVegMeal()
            println("\nNon-Veg Meal")
            nonVegMeal.showItems()
            println("Total Cost: ${nonVegMeal.getCost()}")
        }
    }
}
