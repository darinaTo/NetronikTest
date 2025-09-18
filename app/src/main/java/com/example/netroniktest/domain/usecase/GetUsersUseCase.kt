package com.example.netroniktest.domain.usecase

import com.example.netroniktest.core.NetworkResult
import com.example.netroniktest.domain.model.User
import com.example.netroniktest.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<NetworkResult<List<User>>> {
        return repository.getUsers()
    }
}