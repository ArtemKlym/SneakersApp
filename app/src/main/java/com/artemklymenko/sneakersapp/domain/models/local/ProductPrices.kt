package com.artemklymenko.sneakersapp.domain.models.local

data class ProductPrices(
    val subtotal: Double = 0.0,
    val shipping: Double = 0.0,
    val discount: Double = 0.0,
    val total: Double = 0.0
)
