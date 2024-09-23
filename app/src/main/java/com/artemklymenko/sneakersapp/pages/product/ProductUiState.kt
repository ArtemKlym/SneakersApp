package com.artemklymenko.sneakersapp.pages.product

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.network.product.Product

data class ProductUiState(
    //val product: ProductDetails, local
    val product: Product,
    val isAddedToCart: Boolean
) : UiState