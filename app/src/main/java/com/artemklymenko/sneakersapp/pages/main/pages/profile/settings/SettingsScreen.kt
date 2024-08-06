package com.artemklymenko.sneakersapp.pages.main.pages.profile.settings

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
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Settings",
                onBackClick = { onBackClick.invoke() }
            )
        },
        content = {
            SettingsScreenContent(Modifier.padding(it))
        }
    )
}

@Composable
private fun SettingsScreenContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Settings", fontSize = 22.sp, color = Color.Red)
    }
}