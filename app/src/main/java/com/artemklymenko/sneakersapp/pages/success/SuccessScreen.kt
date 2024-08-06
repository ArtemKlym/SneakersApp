package com.artemklymenko.sneakersapp.pages.success

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
fun SuccessScreen(
    viewModel: SuccessViewModel,
    onConfirmationClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Success",
                isEnableBackIcon = false
            )
        },
        content = {
            SuccessScreenContent(
                modifier = Modifier.padding(it),
                onConfirmationClick = onConfirmationClick
            )
        }
    )
}

@Composable
private fun SuccessScreenContent(
    modifier: Modifier,
    onConfirmationClick: () -> Unit
) {
    Column(modifier = modifier) {
        Text(text = "This is success screen", fontSize = 22.sp, color = Color.Red)
        Text(
            text = "To main screen",
            modifier = Modifier.clickable { onConfirmationClick.invoke() }
        )
    }
}