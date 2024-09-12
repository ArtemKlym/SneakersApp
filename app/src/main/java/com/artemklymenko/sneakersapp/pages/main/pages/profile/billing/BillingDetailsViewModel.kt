package com.artemklymenko.sneakersapp.pages.main.pages.profile.billing

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
class BillingDetailsViewModel @Inject constructor(): BaseViewModel<BillingDetailsUiState, Progress, Dialog, CallBack>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        if(uiEvent == BillingDetailsUiEvent.LoadBillingDetailsData){
            loadData()
        }
    }

    private fun loadData() {
        launch {
            val paymentMethods = async { MockUtils.loadMockPaymentMethods() }
            val response = paymentMethods.await()

            updateState { mutableState ->
                mutableState.value = BillingDetailsUiState(
                    paymentMethods = response
                )
            }
        }
    }
}