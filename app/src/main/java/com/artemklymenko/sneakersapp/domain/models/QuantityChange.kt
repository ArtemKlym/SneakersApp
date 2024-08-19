package com.artemklymenko.sneakersapp.domain.models

sealed class QuantityChange {
    data object Increase: QuantityChange()
    data object Decrease: QuantityChange()
}