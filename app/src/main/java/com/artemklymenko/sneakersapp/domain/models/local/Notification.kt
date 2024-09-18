package com.artemklymenko.sneakersapp.domain.models.local

data class Notification (
    val id: Long,
    val image: String,
    val title: String,
    val description: String,
    val isNew: Boolean
)