package com.artemklymenko.sneakersapp.pages.confirm

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.local.DeliveryAddress
import com.artemklymenko.sneakersapp.domain.models.local.PaymentMethod
import com.artemklymenko.sneakersapp.domain.models.local.ProductCart

data class ConfirmUiState(
    val paymentMethod: PaymentMethod,
    val address: DeliveryAddress,
    val products: List<ProductCart>,
    val total: Double
) : UiState
