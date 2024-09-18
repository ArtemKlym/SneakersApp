package com.artemklymenko.sneakersapp.domain.models.local

data class DeliveryAddress(
    val id: Long,
    val name: String,
    val address: String,
    val isSelected: Boolean
)
