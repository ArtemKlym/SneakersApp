package com.artemklymenko.sneakersapp.network.auth

import com.artemklymenko.sneakersapp.domain.models.network.auth.LoginCredentials
import com.artemklymenko.sneakersapp.domain.models.network.auth.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun auth(@Body loginCredentials: LoginCredentials): User
}