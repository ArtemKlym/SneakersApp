package com.artemklymenko.sneakersapp.pages.main.pages.profile.personal

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
class PersonalDetailsViewModel @Inject constructor():
    BaseViewModel<PersonalDetailsUiState, Progress, Dialog, CallBack>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        if(uiEvent == PersonalDetailsUiEvent.LoadPersonalDetailsData){
            loadData()
        }
    }

    private fun loadData() {
        launch {
            val user = async { MockUtils.loadMockUser() }
            val response = user.await()

            updateState { mutableState ->
                mutableState.value = PersonalDetailsUiState(
                    person = response
                )
            }
        }
    }
}