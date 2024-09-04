package com.artemklymenko.sneakersapp.pages.confirm

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class ConfirmUiEvent : UiEvent {
    data class LoadData(val paymentId: Long, val addressId: Long) : ConfirmUiEvent()
}