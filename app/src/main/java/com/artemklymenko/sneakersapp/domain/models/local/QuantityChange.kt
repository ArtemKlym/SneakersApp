package com.artemklymenko.sneakersapp.domain.models.local

sealed class QuantityChange {
    data object Increase: QuantityChange()
    data object Decrease: QuantityChange()
}