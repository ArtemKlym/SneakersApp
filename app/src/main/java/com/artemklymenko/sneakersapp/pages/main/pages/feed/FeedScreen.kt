package com.artemklymenko.sneakersapp.pages.main.pages.feed

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
fun FeedScreen(
    viewModel: FeedViewModel,
    onNavigateToNotifications: () -> Unit,
    onNavigateToProduct: (Long) -> Unit,
    onNavigateToSearch: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = "Feed", isEnableBackIcon = false)
        },
        content = {
            FeedScreenContent(
                modifier = Modifier.padding(it),
                onNavigateToProduct = onNavigateToProduct,
                onNavigateToNotifications = onNavigateToNotifications,
                onNavigateToSearch = onNavigateToSearch
            )
        }
    )
}

@Composable
private fun FeedScreenContent(
    modifier: Modifier,
    onNavigateToProduct: (Long) -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    Column(modifier = modifier) {
        Text(text = "This is feed screen", fontSize = 22.sp, color = Color.Red)

        Text(
            text = "To notifications",
            modifier = Modifier.clickable { onNavigateToNotifications.invoke() }
        )
        Text(
            text = "To product",
            modifier = Modifier.clickable { onNavigateToProduct.invoke(-1) }
        )
        Text(
            text = "To search",
            modifier = Modifier.clickable { onNavigateToSearch.invoke() }
        )
    }
}
