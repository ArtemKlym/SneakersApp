package com.artemklymenko.sneakersapp.pages.main.pages.favourites

import com.artemklymenko.sneakersapp.core.base.BaseViewModel
import com.artemklymenko.sneakersapp.core.base.common.events.CallBack
import com.artemklymenko.sneakersapp.core.base.common.events.Dialog
import com.artemklymenko.sneakersapp.core.base.common.events.Progress
import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent
import com.artemklymenko.sneakersapp.utils.MockUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor() :
    BaseViewModel<FavouritesUiState, Progress, Dialog, CallBack>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        when(uiEvent) {
            is FavouritesUiEvent.Initiate -> {
                loadFavourites()
            }
            is FavouritesUiEvent.OnItemDeleted -> {
                onItemDeleted(uiEvent.id)
            }
        }
    }

    private fun loadFavourites() {
        launch {
            val data = MockUtils.loadMockFavourites()
            updateState {
                it.value = FavouritesUiState(items = data)
            }
        }
    }

    private fun onItemDeleted(id: Long) {
        updateState { mutableState ->
            mutableState.value?.let { state ->
                val found = state.items.first { it.id == id }
                val newList = state.items.toMutableList().also { it.remove(found) }
                mutableState.value = state.copy(items = newList)
            }
        }
    }
}