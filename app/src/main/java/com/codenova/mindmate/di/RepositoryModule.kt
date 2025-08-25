package com.codenova.mindmate.di

import com.codenova.mindmate.data.mapper.UserMapper
import com.codenova.mindmate.data.remote.api.AuthApiService
import com.codenova.mindmate.data.repositoryImpl.AuthRepositoryImpl
import com.codenova.mindmate.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository
}*/

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: AuthApiService,
        userMapper: UserMapper,
    ): AuthRepository {
        return AuthRepositoryImpl(
            apiService = apiService,
            userMapper = userMapper
        )
    }
}
