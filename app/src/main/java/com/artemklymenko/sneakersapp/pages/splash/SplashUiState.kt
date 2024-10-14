package com.artemklymenko.sneakersapp.pages.splash

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.network.auth.User

data class SplashUiState(
    val user: User?,
    val hasToken: Boolean
): UiState
