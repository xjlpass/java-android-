package com.example.androidlearn.builder.Item

class Meal {
    private val items = ArrayList<Item>()
    fun addItem(item: Item) {
        items.add(item)
    }

    fun getCost(): Float {
        var cost = 0.0f
        for (item in items) {
            cost += item.price()
        }
        return cost
    }

    fun showItems() {
        for (item in items) {
            println(
                "Item: ${item.name()}, Packing: ${
                    item.packing().pack()
                }, Price: ${item.price()}"
            )
        }
    }
}