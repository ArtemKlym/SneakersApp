package com.artemklymenko.sneakersapp.pages.sign_in

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.network.auth.User

data class SignInUiState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) : UiState
