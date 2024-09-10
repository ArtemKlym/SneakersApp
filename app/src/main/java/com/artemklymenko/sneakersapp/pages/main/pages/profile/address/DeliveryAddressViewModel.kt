package com.artemklymenko.sneakersapp.pages.main.pages.profile.address

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
class DeliveryAddressViewModel @Inject constructor() :
    BaseViewModel<DeliveryAddressUiState, Progress, Dialog, CallBack>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        if (uiEvent == DeliveryAddressUiEvent.LoadData) {
            loadData()
        }
    }

    private fun loadData() {
        launch {
            val addresses = async { MockUtils.loadMockAddresses() }
            val response = addresses.await()

            updateState { currentState ->
                currentState.value = DeliveryAddressUiState(
                    addresses = response
                )
            }
        }
    }
}