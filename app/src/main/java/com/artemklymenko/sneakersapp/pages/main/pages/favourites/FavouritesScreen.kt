package com.artemklymenko.sneakersapp.pages.main.pages.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.artemklymenko.sneakersapp.core.components.TopBar

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel,
    onNavigateToProduct: (Long) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = "Favourites", isEnableBackIcon = false)
        },
        content = {
            FavouritesScreenContent(
                modifier = Modifier.padding(it),
                onNavigateToProduct = onNavigateToProduct
            )
        }
    )
}

@Composable
private fun FavouritesScreenContent(
    modifier: Modifier,
    onNavigateToProduct: (Long) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "This is favourites screen",
            fontSize = 22.sp,
            color = Color.Red
        )
        Text(
            text = "To product",
            modifier = Modifier.clickable { onNavigateToProduct.invoke(-1) }
        )
    }
}
