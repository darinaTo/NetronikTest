package com.example.netroniktest.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netroniktest.core.NetworkResult
import com.example.netroniktest.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState(isLoading = true))
    val uiState: StateFlow<UsersUiState> = _uiState

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            getUsersUseCase().collect { result ->
                when (result) {
                    is NetworkResult.Loading -> _uiState.update {
                        it.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is NetworkResult.Success -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            users = result.data
                        )
                    }

                    is NetworkResult.Error -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.exception.message.orEmpty()
                        )
                    }
                }
            }
        }
    }
}