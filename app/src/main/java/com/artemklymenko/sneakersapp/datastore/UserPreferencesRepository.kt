package com.artemklymenko.sneakersapp.datastore

interface UserPreferencesRepository {

    suspend fun saveAuthToken(token: String)

    suspend fun getAuthToken(): Result<String>

    suspend fun clearToken()
}