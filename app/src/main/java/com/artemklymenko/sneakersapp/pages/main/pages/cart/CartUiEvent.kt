package com.artemklymenko.sneakersapp.pages.main.pages.cart

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class CartUiEvent : UiEvent{
    data object LoadScreenData: CartUiEvent()
    data class OnIncreaseQuantity(val id: Long): CartUiEvent()
    data class OnDecreaseQuantity(val id: Long): CartUiEvent()
}
