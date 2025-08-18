package com.codenova.mindmate.di

import com.codenova.mindmate.domain.repository.AuthRepository
import com.codenova.mindmate.domain.usecases.login.LoginUseCase
import com.codenova.mindmate.domain.usecases.login.ValidateEmail
import com.codenova.mindmate.domain.usecases.login.ValidatePassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideValidateEmailUseCase() = ValidateEmail()

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase() = ValidatePassword()
}