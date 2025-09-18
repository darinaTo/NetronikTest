package com.example.netroniktest.domain.model

data class User(
    val id: String = "",
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val country: String = "",
    val city: String = "",
    val picture: String = "",
)
