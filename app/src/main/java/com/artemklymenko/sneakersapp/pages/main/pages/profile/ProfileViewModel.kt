package com.artemklymenko.sneakersapp.pages.main.pages.profile

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
class ProfileViewModel @Inject constructor()
    : BaseViewModel<ProfileUiState, Progress, Dialog, CallBack>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        when(uiEvent){
            is ProfileUiEvent.LoadData -> {
                loadData()
            }
        }
    }

    private fun loadData() {
        launch {
            val user = async { MockUtils.loadMockUser() }
            val response = user.await()

            updateState { currentState ->
                currentState.value = ProfileUiState(
                    id = response.id,
                    name = response.name,
                    surname = response.surname,
                    email = response.email,
                    urlImage = response.urlImage
                )
            }
        }
    }
}