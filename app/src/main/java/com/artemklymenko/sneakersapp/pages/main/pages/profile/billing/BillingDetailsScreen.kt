package com.artemklymenko.sneakersapp.pages.main.pages.profile.billing

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
fun BillingDetailsScreen(
    viewModel: BillingDetailsViewModel,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Billing Details",
                onBackClick = { onBackClick.invoke() }
            )
        },
        content = {
            BillingScreenContent(Modifier.padding(it))
        }
    )
}

@Composable
private fun BillingScreenContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Billing Details", fontSize = 22.sp, color = Color.Red)
    }
}