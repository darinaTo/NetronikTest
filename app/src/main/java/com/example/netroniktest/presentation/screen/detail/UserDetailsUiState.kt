package com.example.netroniktest.presentation.screen.detail

import com.example.netroniktest.domain.model.User

data class UserDetailsUiState(
    val isLoading: Boolean = true,
    val user: User = User(),
    val error: String = ""
)