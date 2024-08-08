package com.artemklymenko.sneakersapp.pages.notifications

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.Notification

data class NotificationsUiState (
    val notifications: List<Notification> = emptyList()
): UiState