package com.artemklymenko.sneakersapp.domain.models.local

data class ProductDetails(
    val id: Long = -1,
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val price: Double = 0.0,
    val imageUrls: List<String> = emptyList(),
    val isFavourite: Boolean = false,
)
