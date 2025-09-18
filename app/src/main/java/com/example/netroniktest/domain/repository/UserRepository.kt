package com.example.netroniktest.domain.repository

import com.example.netroniktest.core.NetworkResult
import com.example.netroniktest.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<NetworkResult<List<User>>>
    fun getUserById(id: String): Flow<User>
}