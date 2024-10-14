package com.artemklymenko.sneakersapp.pages.splash

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class SplashUiEvent : UiEvent{
    data object LoadUserInfo: SplashUiEvent()
}