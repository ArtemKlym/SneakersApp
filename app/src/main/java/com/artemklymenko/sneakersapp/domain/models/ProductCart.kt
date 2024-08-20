package com.artemklymenko.sneakersapp.domain.models

data class ProductCart(
    val id: Long = -1,
    val title: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val category: String = "",
    val quantity: Int = 0
)
