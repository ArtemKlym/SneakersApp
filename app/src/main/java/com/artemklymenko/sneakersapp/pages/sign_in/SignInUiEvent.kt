package com.artemklymenko.sneakersapp.pages.sign_in

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class SignInUiEvent : UiEvent {
    data class Authenticate(val username: String, val password: String): SignInUiEvent()
}