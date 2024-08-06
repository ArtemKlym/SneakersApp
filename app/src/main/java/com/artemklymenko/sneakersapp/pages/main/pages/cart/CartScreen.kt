package com.artemklymenko.sneakersapp.pages.main.pages.cart

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
fun CartScreen(
    viewModel: CartViewModel,
    onNavigateToConfirm: () -> Unit,
    onNavigateToProduct: (Long) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = "Cart", isEnableBackIcon = false)
        },
        content = {
            CartScreenContent(
                modifier = Modifier.padding(it),
                onNavigateToProduct = onNavigateToProduct,
                onNavigateToConfirm = onNavigateToConfirm
            )
        }
    )
}

@Composable
private fun CartScreenContent(
    modifier: Modifier,
    onNavigateToProduct: (Long) -> Unit,
    onNavigateToConfirm: () -> Unit
) {
    Column(modifier = modifier) {
        Text(text = "This is cart screen", fontSize = 22.sp, color = Color.Red)

        Text(
            text = "To confirm",
            modifier = Modifier.clickable { onNavigateToConfirm.invoke() }
        )
        Text(
            text = "To product",
            modifier = Modifier.clickable { onNavigateToProduct.invoke(-1) }
        )
    }
}
