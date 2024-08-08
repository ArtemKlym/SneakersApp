package com.artemklymenko.sneakersapp.domain.models

data class Notification (
    val id: Long,
    val image: String,
    val title: String,
    val description: String,
    val isNew: Boolean
)