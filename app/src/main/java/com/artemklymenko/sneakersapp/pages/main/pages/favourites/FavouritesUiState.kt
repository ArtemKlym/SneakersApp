package com.artemklymenko.sneakersapp.pages.main.pages.favourites

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.ProductDetails

data class FavouritesUiState(
    val items: List<ProductDetails> = emptyList()
) : UiState
