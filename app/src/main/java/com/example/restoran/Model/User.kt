package com.example.restoran.Model

data class User(
    val id: Int,
    val name: String,
    val family: String,
    val userName: String,
    val passWord: String,
    val phone: String
){
    companion object{
        var User: User? =null
    }
}