package com.example.netroniktest.data.di

import com.example.netroniktest.data.repository.UserRepositoryImpl
import com.example.netroniktest.data.source.UserLocalDataSource
import com.example.netroniktest.data.source.UserRemoteDataSource
import com.example.netroniktest.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        remoteDataSource: UserRemoteDataSource,
        localDataSource: UserLocalDataSource
    ): UserRepository = UserRepositoryImpl(remoteDataSource, localDataSource)
}