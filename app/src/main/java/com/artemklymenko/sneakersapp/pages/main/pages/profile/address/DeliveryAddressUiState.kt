package com.artemklymenko.sneakersapp.pages.main.pages.profile.address

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.DeliveryAddress

data class DeliveryAddressUiState(
    val addresses: List<DeliveryAddress>
) : UiState