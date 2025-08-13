package com.codenova.mindmate.di

import com.codenova.mindmate.domain.repository.AuthRepository
import com.codenova.mindmate.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase {
        return LoginUseCase(authRepository)
    }
}