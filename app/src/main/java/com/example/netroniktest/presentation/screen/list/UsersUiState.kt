package com.example.netroniktest.presentation.screen.list

import com.example.netroniktest.domain.model.User

data class UsersUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)