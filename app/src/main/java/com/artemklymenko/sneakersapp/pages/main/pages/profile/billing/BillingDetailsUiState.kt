package com.artemklymenko.sneakersapp.pages.main.pages.profile.billing

import com.artemklymenko.sneakersapp.core.base.common.state.UiState
import com.artemklymenko.sneakersapp.domain.models.local.PaymentMethod

data class BillingDetailsUiState(
    val paymentMethods: List<PaymentMethod>
) : UiState
