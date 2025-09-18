package com.example.netroniktest.data.repository

import com.example.netroniktest.core.NetworkResult
import com.example.netroniktest.core.networkBoundResource
import com.example.netroniktest.data.mapper.toDomain
import com.example.netroniktest.data.source.UserLocalDataSource
import com.example.netroniktest.data.source.UserRemoteDataSource
import com.example.netroniktest.domain.model.User
import com.example.netroniktest.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override fun getUsers(): Flow<NetworkResult<List<User>>> {
        return networkBoundResource(
            query = {
                localDataSource.getUsers()
            },
            fetch = {
                remoteDataSource.getUsers().getOrThrow()
            },
            saveFetchResult = { response ->
                val users = response.results.map { it.toDomain() }
                localDataSource.saveUsers(users)
            },
            mapFetched = { response ->
                response.results.map { it.toDomain() }
            }
        )
    }

    override fun getUserById(id: String): Flow<User> =
        localDataSource.getUser(id)

}