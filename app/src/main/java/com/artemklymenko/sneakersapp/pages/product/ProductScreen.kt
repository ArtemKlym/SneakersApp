package com.artemklymenko.sneakersapp.pages.product

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
fun ProductScreen(
    viewModel: ProductViewModel,
    onBackClick: () -> Unit
) {
    Scaffold (
       topBar = {
           TopBar(title = "Product", onBackClick)
       },
        content = {
            ProductScreenContent(
                modifier = Modifier.padding(it)
            )
        }
    )
}

@Composable
private fun ProductScreenContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "This is product screen", fontSize = 22.sp, color = Color.Red)
    }
}
