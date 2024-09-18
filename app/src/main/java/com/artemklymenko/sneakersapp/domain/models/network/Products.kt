package com.artemklymenko.sneakersapp.domain.models.network

data class Products(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
