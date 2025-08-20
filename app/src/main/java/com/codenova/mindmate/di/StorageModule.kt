package com.codenova.mindmate.di

import android.content.Context
import com.codenova.mindmate.data.repository.TokenRepositoryImpl
import com.codenova.mindmate.domain.repository.TokenRepository
import com.codenova.mindmate.framework.local.CryptoManager
import com.codenova.mindmate.framework.local.TokenStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideCryptoManager(): CryptoManager = CryptoManager()

    @Provides
    @Singleton
    fun provideTokenStorage(
        @ApplicationContext context: Context,
        cryptoManager: CryptoManager
    ): TokenStorage = TokenStorage(context, cryptoManager)

    @Provides
    @Singleton
    fun provideTokenRepository(
        tokenStorage: TokenStorage
    ): TokenRepository = TokenRepositoryImpl(tokenStorage)
}
