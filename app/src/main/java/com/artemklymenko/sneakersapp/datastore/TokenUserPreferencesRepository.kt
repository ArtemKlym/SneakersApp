package com.artemklymenko.sneakersapp.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.artemklymenko.sneakersapp.utils.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenUserPreferencesRepository @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
) : UserPreferencesRepository {

    override suspend fun saveAuthToken(token: String) {
        Result.runCatching {
            userDataStorePreferences.edit { preferences ->
                preferences[KEY_NAME] = token
            }
        }
    }

    override suspend fun getAuthToken(): Result<String> {
        return Result.runCatching {
            val flow = userDataStorePreferences.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[KEY_NAME]
                }
            val value = flow.firstOrNull() ?: ""
            value
        }
    }

    override suspend fun clearToken() {
        userDataStorePreferences.edit { preferences ->
            preferences.clear()
        }
    }

    private companion object {

        val KEY_NAME = stringPreferencesKey(
            name = Constants.AUTH_TOKEN_KEY
        )
    }
}