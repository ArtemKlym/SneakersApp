package com.artemklymenko.sneakersapp.pages.main.pages.profile.personal

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class PersonalDetailsUiEvent : UiEvent {
    data object LoadPersonalDetailsData: PersonalDetailsUiEvent()
}