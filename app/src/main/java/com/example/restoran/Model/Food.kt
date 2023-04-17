package com.example.restoran.Model

data class Food(
    val id: Int,
    val image: String,
    val many: Double,
    val name: String,
    val restaurantId: String
)