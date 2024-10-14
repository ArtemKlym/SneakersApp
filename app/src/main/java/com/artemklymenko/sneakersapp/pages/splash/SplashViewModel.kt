package com.artemklymenko.sneakersapp.pages.splash

import com.artemklymenko.sneakersapp.core.base.BaseViewModel
import com.artemklymenko.sneakersapp.core.base.common.events.CallBack
import com.artemklymenko.sneakersapp.core.base.common.events.Dialog
import com.artemklymenko.sneakersapp.core.base.common.events.Progress
import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent
import com.artemklymenko.sneakersapp.datastore.UserPreferencesRepository
import com.artemklymenko.sneakersapp.network.auth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : BaseViewModel<SplashUiState, Progress, Dialog, CallBack>() {

    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is SplashUiEvent.LoadUserInfo -> {
                fetchAndLoadUser()
            }
        }
    }

    private fun fetchAndLoadUser() {
        launch {
            val result = userPreferencesRepository.getAuthToken()
            val token = result.getOrNull().orEmpty()
            if (token.isEmpty()) {
                updateState { currentState ->
                    currentState.value = SplashUiState(
                        user = null,
                        hasToken = false
                    )
                }
            } else {
                loadUser(token)
            }
        }
    }

    private fun loadUser(token: String) {
        launch {
            val response = authRepository.getTokenUser(token)

            updateState { currentState ->
                response.body()?.let { user ->
                    currentState.value = SplashUiState(
                        user = user,
                        hasToken = true
                    )
                }
            }
        }
    }
}