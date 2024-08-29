package com.artemklymenko.sneakersapp.pages.main.pages.profile

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class ProfileUiEvent : UiEvent {
    data object LoadData: ProfileUiEvent()
}