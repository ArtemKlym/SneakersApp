package com.artemklymenko.sneakersapp.pages.main.pages.favourites

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class FavouritesUiEvent : UiEvent {
    data object Initiate: FavouritesUiEvent()
    data class OnItemDeleted(val id: Long): FavouritesUiEvent()
}