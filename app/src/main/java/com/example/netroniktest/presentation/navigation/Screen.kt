package com.example.netroniktest.presentation.navigation

object Screen {
    const val USERS = "users"
    const val DETAILS = "details/{userId}"

    fun details(userId: String) = "details/$userId"
}
