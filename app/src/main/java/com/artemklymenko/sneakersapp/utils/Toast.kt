package com.artemklymenko.sneakersapp.utils

import android.content.Context
import android.widget.Toast

fun appToastShow(message: String, context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}