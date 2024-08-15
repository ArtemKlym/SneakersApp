package com.artemklymenko.sneakersapp.pages.product

import androidx.lifecycle.ViewModel
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
class ProductViewModel @Inject constructor(): BaseViewModel<ProductUiState, Progress, Dialog, CallBack>() {
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

    private fun loadData(id: Long) {
        launch {
            val product = async { MockUtils.loadMockProductDetails(id) }
            val isProductInCart = async { MockUtils.isProductInCart(id) }
            val response = product.await() to isProductInCart.await()

            updateState { currentState ->
                currentState.value = ProductUiState(
                    product = response.first,
                    isAddedToCart = response.second
                )
            }
        }
    }

    private fun updateFavouriteState() {
        updateState { mutableState ->
            mutableState.value?.let { state ->
                val product = state.product.copy(isFavourite = !state.product.isFavourite)
                mutableState.value = state.copy(product = product)
            }
        }
    }
}