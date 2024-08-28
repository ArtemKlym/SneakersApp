package com.artemklymenko.sneakersapp.pages.main.pages.feed

import com.artemklymenko.sneakersapp.core.base.BaseViewModel
import com.artemklymenko.sneakersapp.core.base.common.events.CallBack
import com.artemklymenko.sneakersapp.core.base.common.events.Dialog
import com.artemklymenko.sneakersapp.core.base.common.events.Progress
import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent
import com.artemklymenko.sneakersapp.utils.MockUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(): BaseViewModel<FeedUiState, Progress, Dialog, CallBack>(){
    override fun handleUiEvent(uiEvent: UiEvent) {
        when(uiEvent){
            is FeedUiEvent.LoadScreenData -> {
                loadScreenData()
            }
            is FeedUiEvent.OnFavouriteClick -> {
                onFavouriteClick(uiEvent.favouriteId)
            }
            is FeedUiEvent.OnProductClick -> {
                /* no-op */
            }
            is FeedUiEvent.OnSearchQueryChange -> {
                onSearchQueryChange(uiEvent.query)
            }
        }
    }

    private fun onSearchQueryChange(query: String) {
        val currentState = uiState.value
        if (currentState != null) {
            val filteredProducts = currentState.products.filter {
                it.title.contains(query, ignoreCase = true)
            }
            updateState {
                it.value = currentState.copy(
                    searchQuery = query,
                    filteredProducts = filteredProducts
                )
            }
        }
    }

    fun onSearch(query: String) {
        handleUiEvent(FeedUiEvent.OnSearchQueryChange(query))
    }

    private fun onFavouriteClick(favouriteId: Long) {
        TODO("Not yet implemented")
    }

    private fun loadScreenData() {
        launch {
            val products = async { MockUtils.loadMockProducts() }
            val searchCategories = async { MockUtils.loadMockSearchCategories()}
            val response = products.await() to searchCategories.await()

            updateState { currentState ->
                currentState.value = FeedUiState(
                    products = response.first,
                    filteredProducts = response.first,
                    searchCategories = response.second
                )
            }
        }
    }
}