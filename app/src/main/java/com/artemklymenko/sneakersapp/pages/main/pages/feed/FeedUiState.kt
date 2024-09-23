package com.artemklymenko.sneakersapp.pages.main.pages.feed

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.network.product.Product

data class FeedUiState(
    val products: List<Product>,
   // val products: List<Product> = emptyList(), Local products
    val searchCategories: List<String> = emptyList(),
    val searchQuery: String = "",
    val filteredProducts: List<Product> = emptyList()
    //val filteredProducts: List<Product> = emptyList() Local products
) : UiState