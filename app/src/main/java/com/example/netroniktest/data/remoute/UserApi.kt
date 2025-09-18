package com.example.netroniktest.data.remoute

import com.example.netroniktest.data.remoute.dto.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("api/")
    suspend fun getUsers(@Query("results") results: Int = 20): Response<UserResponse>
}
