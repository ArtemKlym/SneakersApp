package com.artemklymenko.sneakersapp.network.auth.repository

import com.artemklymenko.sneakersapp.domain.models.network.auth.LoginCredentials
import com.artemklymenko.sneakersapp.domain.models.network.auth.User
import com.artemklymenko.sneakersapp.network.auth.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {

    suspend fun authorization(loginCredentials: LoginCredentials): User {
        return authApi.auth(loginCredentials)
    }
}