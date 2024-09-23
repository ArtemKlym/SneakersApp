package com.artemklymenko.sneakersapp.domain.models.network.product

data class Products(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
