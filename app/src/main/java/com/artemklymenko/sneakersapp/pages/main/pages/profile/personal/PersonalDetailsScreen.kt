package com.artemklymenko.sneakersapp.pages.main.pages.profile.personal

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
fun PersonalDetailsScreen(
    viewModel: PersonalDetailsViewModel,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Personal Details",
                onBackClick = { onBackClick.invoke() }
            )
        },
        content = {
            PersonalDetailsScreenContent(Modifier.padding(it))
        }
    )
}

@Composable
private fun PersonalDetailsScreenContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Personal Details", fontSize = 22.sp, color = Color.Red)
    }
}