package com.artemklymenko.sneakersapp.pages.main.pages.profile.address

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class DeliveryAddressUiEvent : UiEvent {
    data object LoadData: DeliveryAddressUiEvent()
}