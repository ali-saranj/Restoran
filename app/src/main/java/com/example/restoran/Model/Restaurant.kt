package com.example.restoran.Model

class Restaurant(
    val description: String,
    val id: Int,
    val imageTitleUrls: String,
    val imageUrls: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val phone: String,
    val rating: Float,
    val timeWork: String
){
    fun get_imageUrls():List<String>{
        return imageUrls.split(",")
    }

}
