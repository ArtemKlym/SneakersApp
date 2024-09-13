package com.artemklymenko.sneakersapp.pages.main.pages.profile.personal

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.User

data class PersonalDetailsUiState (
    val person: User
) : UiState