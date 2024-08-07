package com.artemklymenko.sneakersapp.core.base.common.uidata

import com.artemklymenko.sneakersapp.core.base.common.events.Progress

data class ProgressData<P: Progress>(
    val progress: P? = null
)