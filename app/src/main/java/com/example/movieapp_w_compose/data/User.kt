package com.example.movieapp_w_compose.data

data class User(
    val userId: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val imageUrl: String = "",
    val token:String? = null,
    val phone:String = ""
)
