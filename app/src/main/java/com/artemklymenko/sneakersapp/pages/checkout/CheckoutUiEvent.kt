package com.artemklymenko.sneakersapp.pages.checkout

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class CheckoutUiEvent : UiEvent {
    data object LoadScreenData: CheckoutUiEvent()
    data class SelectDeliveryAddress(val id: Long): CheckoutUiEvent()
    data class SelectPaymentMethod(val id: Long): CheckoutUiEvent()
}