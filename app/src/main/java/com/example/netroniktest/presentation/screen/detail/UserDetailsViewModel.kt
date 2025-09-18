package com.example.netroniktest.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netroniktest.domain.usecase.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserById: GetUserByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val userId: String = requireNotNull(savedStateHandle["userId"])

    private val _uiState = MutableStateFlow(UserDetailsUiState(isLoading = true))
    val uiState: StateFlow<UserDetailsUiState> = _uiState

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            getUserById(userId).collect { user ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    user = user,
                    error = ""
                )
            }
        }
    }
}

