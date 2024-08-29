package com.artemklymenko.sneakersapp.pages.main.pages.profile

import com.artemklymenko.sneakersapp.core.base.common.state.UiState

data class ProfileUiState (
    val id: Long,
    val email: String,
    val name: String,
    val surname: String,
    val urlImage: String
) : UiState