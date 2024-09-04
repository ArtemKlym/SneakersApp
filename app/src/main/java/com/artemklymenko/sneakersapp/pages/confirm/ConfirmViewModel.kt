package com.artemklymenko.sneakersapp.pages.confirm

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
class ConfirmViewModel @Inject constructor(): BaseViewModel<ConfirmUiState, Progress, Dialog, CallBack>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        when(uiEvent){
            is ConfirmUiEvent.LoadData -> {
                loadData(uiEvent.paymentId, uiEvent.addressId)
            }
        }
    }

    private fun loadData(paymentId: Long, addressId: Long) {
        launch {
            val paymentMethodsResponse = async { MockUtils.loadMockPaymentMethods() }
            val addressResponse = async { MockUtils.loadMockAddresses() }
            val cartResponse = async {MockUtils.loadMockCart()}
            val priceResponse = async { MockUtils.loadMockProductPrices()}

            val (addresses, paymentMethods) = addressResponse.await() to paymentMethodsResponse.await()
            val (cart, prices) = cartResponse.await() to priceResponse.await()

            updateState { mutableState ->
                mutableState.value = ConfirmUiState(
                    paymentMethod = paymentMethods[paymentId.toInt() - 1],
                    address = addresses[addressId.toInt() - 1 ],
                    products = cart,
                    total = prices.total
                )
            }
        }
    }
}