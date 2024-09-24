package com.artemklymenko.sneakersapp.utils

import androidx.lifecycle.ViewModel
import com.artemklymenko.sneakersapp.domain.models.network.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    var user: User? = null
        private set

    fun setUser(user: User) {
        this.user = user
    }
}