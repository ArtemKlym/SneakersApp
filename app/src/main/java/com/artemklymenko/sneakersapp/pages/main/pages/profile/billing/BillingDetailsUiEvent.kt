package com.artemklymenko.sneakersapp.pages.main.pages.profile.billing

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class BillingDetailsUiEvent : UiEvent {
    data object LoadBillingDetailsData: BillingDetailsUiEvent()
}