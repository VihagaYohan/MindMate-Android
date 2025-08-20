package com.codenova.mindmate.framework.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_tokens")

@Singleton
class TokenStorage @Inject constructor(
    private val context: Context,
    private val cryptoManager: CryptoManager
) {
    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("encrypted_access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("encrypted_refresh_token")
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        val encryptedAccessToken =
            cryptoManager.encrypt(accessToken.toByteArray(Charset.defaultCharset()))
        val encryptedRefreshToken =
            cryptoManager.encrypt(refreshToken.toByteArray(Charset.defaultCharset()))

        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = android.util.Base64.encodeToString(
                encryptedAccessToken,
                android.util.Base64.DEFAULT
            )
            preferences[REFRESH_TOKEN_KEY] = android.util.Base64.encodeToString(
                encryptedRefreshToken,
                android.util.Base64.DEFAULT
            )
        }
    }

    val accessTokenFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN_KEY]?.let { encryptedTokenString ->
                try {
                    val decodedBytes = android.util.Base64.decode(
                        encryptedTokenString,
                        android.util.Base64.DEFAULT
                    )
                    cryptoManager.decrypt(decodedBytes).toString(Charset.defaultCharset())
                } catch (e: Exception) {
                    null
                }
            }
        }

    val refreshTokenFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[REFRESH_TOKEN_KEY]?.let { encryptedTokenString ->
                try {
                    val decodedBytes = android.util.Base64.decode(
                        encryptedTokenString,
                        android.util.Base64.DEFAULT
                    )
                    cryptoManager.decrypt(decodedBytes).toString(Charset.defaultCharset())
                } catch (e: Exception) {
                    null
                }
            }
        }

    suspend fun getAccessToken(): String? = accessTokenFlow.firstOrNull()
    suspend fun getRefreshToken(): String? = refreshTokenFlow.firstOrNull()

    suspend fun clearTokens() {
        context.dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }
}
