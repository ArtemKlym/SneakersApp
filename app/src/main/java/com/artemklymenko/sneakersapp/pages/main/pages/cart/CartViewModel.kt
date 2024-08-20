package com.artemklymenko.sneakersapp.pages.main.pages.cart

import com.artemklymenko.sneakersapp.core.base.BaseViewModel
import com.artemklymenko.sneakersapp.core.base.common.events.CallBack
import com.artemklymenko.sneakersapp.core.base.common.events.Dialog
import com.artemklymenko.sneakersapp.core.base.common.events.Progress
import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent
import com.artemklymenko.sneakersapp.domain.models.QuantityChange
import com.artemklymenko.sneakersapp.utils.MockUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() :
    BaseViewModel<CartUiState, Progress, Dialog, CallBack>() {

    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is CartUiEvent.LoadScreenData -> {
                loadData()
            }

            is CartUiEvent.OnIncreaseQuantity -> {
                changeQuantity(uiEvent.id, QuantityChange.Increase)
            }

            is CartUiEvent.OnDecreaseQuantity -> {
                changeQuantity(uiEvent.id, QuantityChange.Decrease)
            }
        }
    }

    private fun changeQuantity(id: Long, action: QuantityChange) {
        updateState { mutableState ->
            mutableState.value?.let { state ->
                val products = state.products.map { product ->
                    val quantity = if (action is QuantityChange.Increase) {
                        product.quantity + 1
                    } else {
                        product.quantity - 1
                    }

                    if (product.id == id) product.copy(quantity = quantity.coerceAtLeast(1))
                    else product
                }
                mutableState.value = state.copy(products = products)
            }
        }
    }

    private fun loadData() {
        launch {
            val productResponse = async { MockUtils.loadMockCart() }
            val pricesResponse = async { MockUtils.loadMockProductPrices() }
            val (product, prices) = productResponse.await() to pricesResponse.await()

            updateState { currentState ->
                currentState.value = CartUiState(
                    products = product,
                    subtotal = prices.subtotal,
                    shipping = prices.shipping,
                    discount = prices.discount,
                    total = prices.total
                )
            }
        }
    }

}