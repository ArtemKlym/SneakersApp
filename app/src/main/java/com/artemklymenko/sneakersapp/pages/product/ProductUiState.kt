package com.artemklymenko.sneakersapp.pages.product

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.ProductDetails

data class ProductUiState(
    val product: ProductDetails,
    val isAddedToCart: Boolean
) : UiState