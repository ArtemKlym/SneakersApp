package com.artemklymenko.sneakersapp.pages.checkout

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
class CheckoutViewModel @Inject constructor()
    : BaseViewModel<CheckoutUiState, Progress, Dialog, CallBack>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        when(uiEvent){
            is CheckoutUiEvent.LoadScreenData -> {
                loadScreenData()
            }
            is CheckoutUiEvent.SelectPaymentMethod -> {
                updatePaymentMethod(uiEvent.id)
            }
            is CheckoutUiEvent.SelectDeliveryAddress -> {
                updateDeliveryAddress(uiEvent.id)
            }
        }
    }

    private fun updateDeliveryAddress(id: Long) {
        updateState { mutableState ->
            mutableState.value?.let { state ->
                val deliveryAddress = state.addresses.map { deliveryAddress ->
                    deliveryAddress.copy(isSelected = deliveryAddress.id == id)
                }
                mutableState.value = state.copy(addresses = deliveryAddress)
            }
        }
    }

    private fun updatePaymentMethod(id: Long) {
        updateState { mutableState ->
            mutableState.value?.let { state ->
                val paymentMethod = state.paymentMethods.map { paymentMethod ->
                    paymentMethod.copy(isSelected = paymentMethod.id == id)
                }
                mutableState.value = state.copy(paymentMethods = paymentMethod)
            }
        }
    }

    private fun loadScreenData() {
        launch {
            val addressResponse = async { MockUtils.loadMockAddresses() }
            val paymentMethodsResponse = async { MockUtils.loadMockPaymentMethods() }
            val cartResponse = async {MockUtils.loadMockCart()}
            val priceResponse = async { MockUtils.loadMockProductPrices()}

            val (addresses, paymentMethods) = addressResponse.await() to paymentMethodsResponse.await()
            val (cart, prices) = cartResponse.await() to priceResponse.await()

            updateState { mutableState ->
                mutableState.value = CheckoutUiState(
                    addresses = addresses,
                    paymentMethods = paymentMethods,
                    products = cart,
                    total = prices.total
                )
            }
        }
    }
}