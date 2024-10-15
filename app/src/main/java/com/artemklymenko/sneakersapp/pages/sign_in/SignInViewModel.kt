package com.artemklymenko.sneakersapp.pages.sign_in

import androidx.lifecycle.viewModelScope
import com.artemklymenko.sneakersapp.core.base.BaseViewModel
import com.artemklymenko.sneakersapp.core.base.common.events.CallBack
import com.artemklymenko.sneakersapp.core.base.common.events.Dialog
import com.artemklymenko.sneakersapp.core.base.common.events.Progress
import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent
import com.artemklymenko.sneakersapp.datastore.UserPreferencesRepository
import com.artemklymenko.sneakersapp.domain.models.network.auth.LoginCredentials
import com.artemklymenko.sneakersapp.network.auth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
): BaseViewModel<SignInUiState, Progress, Dialog, CallBack>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is SignInUiEvent.Authenticate -> {
                loginRequest(uiEvent.username, uiEvent.password)
            }
        }
    }

    private fun loginRequest(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.authorization(LoginCredentials(username, password))
                userPreferencesRepository.saveAuthToken(response.refreshToken)
                updateState { currentState ->
                    currentState.value = SignInUiState(
                        user = response,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                updateState { currentState ->
                    currentState.value = SignInUiState(
                        user = null,
                        isLoading = false,
                        errorMessage = "Authentication failed: ${e.message}"
                    )
                }
            }
        }
    }
}