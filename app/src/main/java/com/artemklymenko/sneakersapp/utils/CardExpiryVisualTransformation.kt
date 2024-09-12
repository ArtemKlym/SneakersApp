package com.artemklymenko.sneakersapp.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardExpiryVisualTransformation : VisualTransformation {
    override fun filter(text:AnnotatedString):TransformedText {
        val trimmedText = text.text.take(4)
        val formattedText = buildString {
            if (trimmedText.length >= 2) {
                append(trimmedText.substring(0, 2))
                append("/")
                if (trimmedText.length > 2) {
                    append(trimmedText.substring(2))
                }
            } else {
                append(trimmedText)
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 4 -> offset + 1
                    else -> 5
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 5 -> offset - 1
                    else -> 4
                }
            }
        }

        return TransformedText(
            AnnotatedString(formattedText),
            offsetMapping
        )
    }
}