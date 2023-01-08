package com.example.restoran.Model

data class Comment(
    val id: Int,
    val restaurantId: String,
    val userId: String,
    val username: String,
    val comment: String,
)