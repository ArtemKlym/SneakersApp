package com.artemklymenko.sneakersapp.core.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.artemklymenko.sneakersapp.R

@Composable
fun ProductPrice(
    modifier: Modifier,
    price: Double,
    style: TextStyle,
) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.pattern_price, price),
        style = style,
        maxLines = 1,
        color = MaterialTheme.colorScheme.error,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ProductDescription(
    description: String,
    maxLines: Int
) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ProductTitle(
    modifier: Modifier,
    title: String,
    style: TextStyle
) {
    Text(
        modifier = modifier,
        text = title,
        style = style,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ProductCategory(category: String) {
    Text(
        text = category,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}