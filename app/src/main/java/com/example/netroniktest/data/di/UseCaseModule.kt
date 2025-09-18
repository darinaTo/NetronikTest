package com.example.netroniktest.data.di

import com.example.netroniktest.domain.repository.UserRepository
import com.example.netroniktest.domain.usecase.GetUserByIdUseCase
import com.example.netroniktest.domain.usecase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetUsersUseCase(
        repository: UserRepository
    ): GetUsersUseCase = GetUsersUseCase(repository)


    @Provides
    @Singleton
    fun provideGetUserByIDUseCase(
        repository: UserRepository
    ): GetUserByIdUseCase = GetUserByIdUseCase(repository)
}