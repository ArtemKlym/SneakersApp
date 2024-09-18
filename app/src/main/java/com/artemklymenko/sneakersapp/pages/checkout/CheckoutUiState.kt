package com.artemklymenko.sneakersapp.pages.checkout

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.local.DeliveryAddress
import com.artemklymenko.sneakersapp.domain.models.local.PaymentMethod
import com.artemklymenko.sneakersapp.domain.models.local.ProductCart

data class CheckoutUiState (
    val addresses: List<DeliveryAddress>,
    val paymentMethods: List<PaymentMethod>,
    val products: List<ProductCart>,
    val total: Double
) : UiState