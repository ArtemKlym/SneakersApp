package com.artemklymenko.sneakersapp.pages.confirm

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
fun ConfirmScreen(
    viewModel: ConfirmViewModel,
    onNavigateToSuccess: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = "Confirm", onBackClick)
        },
        content = {
            ConfirmContent(
                modifier = Modifier.padding(it),
                onNavigateToSuccess = onNavigateToSuccess
            )
        }
    )
}

@Composable
private fun ConfirmContent(modifier: Modifier, onNavigateToSuccess: () -> Unit) {
    Column(modifier = modifier) {
        Text(text = "This is confirmation screen", fontSize = 22.sp, color = Color.Red)
        Text(
            text = "To success",
            modifier = Modifier.clickable { onNavigateToSuccess.invoke() }
        )
    }
}
