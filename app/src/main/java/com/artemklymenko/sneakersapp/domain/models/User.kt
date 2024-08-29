package com.artemklymenko.sneakersapp.domain.models

data class User(
    val id: Long,
    val email: String,
    val name: String,
    val surname: String,
    val urlImage: String
)
