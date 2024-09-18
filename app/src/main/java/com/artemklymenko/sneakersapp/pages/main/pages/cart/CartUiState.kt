package com.artemklymenko.sneakersapp.pages.main.pages.cart

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.local.ProductCart

data class CartUiState(
    val products: List<ProductCart> = emptyList(),
    val subtotal: Double = 0.0,
    val shipping: Double = 0.0,
    val discount: Double = 0.0,
    val total: Double = 0.0
) : UiState
