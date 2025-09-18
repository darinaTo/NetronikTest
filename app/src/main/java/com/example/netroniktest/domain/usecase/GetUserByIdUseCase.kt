package com.example.netroniktest.domain.usecase

import com.example.netroniktest.domain.model.User
import com.example.netroniktest.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(id: String): Flow<User> = repository.getUserById(id)
}
