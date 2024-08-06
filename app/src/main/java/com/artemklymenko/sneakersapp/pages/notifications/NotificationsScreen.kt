package com.artemklymenko.sneakersapp.pages.notifications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.components.TopBar

@Composable
fun NotificationsScreen(
    viewModel: NotificationsViewModel,
    onBackClick: () -> Unit
) {
    Scaffold (
        topBar = {
            TopBar(
                title = stringResource(id = R.string.notifications),
                onBackClick = onBackClick
            )
        },
        content = {
            NotificationsScreenContent(
                modifier = Modifier.padding(it)
            )
        }
    )
}

@Composable
private fun NotificationsScreenContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "This is notification screen", fontSize = 22.sp, color = Color.Red)
    }
}
