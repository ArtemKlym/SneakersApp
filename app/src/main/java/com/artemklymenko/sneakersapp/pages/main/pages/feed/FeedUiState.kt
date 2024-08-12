package com.artemklymenko.sneakersapp.pages.main.pages.feed

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.Product

data class FeedUiState(
    val products: List<Product> = emptyList(),
    val searchCategories: List<String> = emptyList()
) : UiState