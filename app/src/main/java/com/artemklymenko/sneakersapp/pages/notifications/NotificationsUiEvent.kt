package com.artemklymenko.sneakersapp.pages.notifications

import com.artemklymenko.sneakersapp.core.base.common.events.UiEvent

sealed class NotificationsUiEvent: UiEvent {

    data object LoadScreenData : NotificationsUiEvent()

    data class OnMarkAsReadClick(val id: Long): NotificationsUiEvent()

    data object OnMarkAllAsReadClick : NotificationsUiEvent()
}