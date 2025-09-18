package com.example.netroniktest.data.source

import com.example.netroniktest.data.local.UserDao
import com.example.netroniktest.data.mapper.toDomain
import com.example.netroniktest.data.mapper.toEntity
import com.example.netroniktest.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val dao: UserDao
) {

    fun getUsers(): Flow<List<User>> =
        dao.getUsers().map { list -> list.map { it.toDomain() } }

    fun getUser(id: String): Flow<User> =
        dao.getUser(id).map { it.toDomain() }

    suspend fun saveUsers(users: List<User>) {
        dao.insertUsers(users.map { it.toEntity() })
    }
}