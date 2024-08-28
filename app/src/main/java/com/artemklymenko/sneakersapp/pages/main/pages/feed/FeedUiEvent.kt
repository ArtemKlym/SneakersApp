package com.artemklymenko.sneakersapp.pages.main.pages.feed

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class FeedUiEvent : UiEvent {
    data object LoadScreenData : FeedUiEvent()

    data class OnProductClick(val productId: Long) : FeedUiEvent()

    data class OnFavouriteClick(val favouriteId: Long) : FeedUiEvent()

    data class OnSearchQueryChange(val query: String) : FeedUiEvent()
}