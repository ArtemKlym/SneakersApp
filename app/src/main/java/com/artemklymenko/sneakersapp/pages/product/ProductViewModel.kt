package com.artemklymenko.sneakersapp.pages.product

import androidx.lifecycle.viewModelScope
import com.artemklymenko.sneakersapp.core.base.BaseViewModel
import com.artemklymenko.sneakersapp.core.base.common.events.CallBack
import com.artemklymenko.sneakersapp.core.base.common.events.Dialog
import com.artemklymenko.sneakersapp.core.base.common.events.Progress
import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent
import com.artemklymenko.sneakersapp.network.product.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
): BaseViewModel<ProductUiState, Progress, Dialog, CallBack>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        when(uiEvent){
            is ProductUiEvent.LoadData -> {
                loadData(uiEvent.id)
            }
            is ProductUiEvent.AddToCartOnClick -> {
                //TBD
            }
            is ProductUiEvent.UpdateFavouriteOnClick -> {
                updateFavouriteState()
            }
        }
    }

    private fun loadData(id: Int) {
         viewModelScope.launch {
            val response = repository.fetchShoes(id)

            updateState { currentState ->
                currentState.value = ProductUiState(
                    product = response,
                    isAddedToCart = false
                )
            }
        }
    }

    private fun updateFavouriteState() {
//        updateState { mutableState ->
//            mutableState.value?.let { state ->
//                val product = state.product.copy(isFavourite = !state.product.isFavourite)
//                mutableState.value = state.copy(product = product)
//            }
//        }
    }
}