package com.artemklymenko.sneakersapp.pages.product

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class ProductUiEvent : UiEvent {
    data class LoadData(val id: Int) : ProductUiEvent()
    data object AddToCartOnClick : ProductUiEvent()
    data object UpdateFavouriteOnClick: ProductUiEvent()
}