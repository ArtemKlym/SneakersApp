package com.artemklymenko.sneakersapp.domain.models

data class PaymentMethod(
    val id: Long,
    val name: String,
    val number: String,
    val isSelected: Boolean
)
