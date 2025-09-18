package com.example.netroniktest.data.source

import com.example.netroniktest.data.remoute.UserApi
import com.example.netroniktest.data.remoute.dto.UserResponse
import java.io.IOException
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val api: UserApi
) {
    suspend fun getUsers(): Result<UserResponse> {
        val response = api.getUsers()
        return if (response.isSuccessful) {
            Result.success(response.body() ?: throw IOException("No data received."))
        } else {
            Result.failure(IOException("Request failed with code ${response.code()}"))
        }
    }
}