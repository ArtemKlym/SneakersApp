package com.artemklymenko.sneakersapp.domain.models.network

data class Review(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)
