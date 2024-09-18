package com.artemklymenko.sneakersapp.pages.main.pages.feed

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.artemklymenko.sneakersapp.core.base.BaseViewModel
import com.artemklymenko.sneakersapp.core.base.common.events.CallBack
import com.artemklymenko.sneakersapp.core.base.common.events.Dialog
import com.artemklymenko.sneakersapp.core.base.common.events.Progress
import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent
import com.artemklymenko.sneakersapp.network.repository.ProductRepository
import com.artemklymenko.sneakersapp.utils.MockUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val productRepository: ProductRepository
): BaseViewModel<FeedUiState, Progress, Dialog, CallBack>(){
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
        viewModelScope.launch {
            try {
                val response = productRepository.getAllShoes()

                updateState { currentState ->
                    currentState.value = FeedUiState(
                        products = response.products,
                        filteredProducts = response.products,
                        searchCategories = MockUtils.loadMockSearchCategories()
                    )
                }
            }catch (e: Exception){
                Log.e("FeedViewModel", "Failed to load screen data: ${e.message}")
            }
        }
//        Load local products
//        launch {
//            val products = async { MockUtils.loadMockProducts() }
//            val searchCategories = async { MockUtils.loadMockSearchCategories()}
//            val response = products.await() to searchCategories.await()
//
//            updateState { currentState ->
//                currentState.value = FeedUiState(
//                    products = response.first,
//                    filteredProducts = response.first,
//                    searchCategories = response.second
//                )
//            }
//        }
    }
}