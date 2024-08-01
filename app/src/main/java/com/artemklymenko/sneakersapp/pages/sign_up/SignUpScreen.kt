package com.artemklymenko.sneakersapp.pages.sign_up

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    onNavigationNext: () -> Unit
) {
    Scaffold(
        topBar = { TopBar() },
        content = {
            SignInScreenContent(
                modifier = Modifier.padding(it),
                onNavigationNext = onNavigationNext
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Sign-up",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}

@Composable
private fun SignInScreenContent(
    modifier: Modifier,
    onNavigationNext: () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Sign-up screen",
            modifier = modifier.clickable {
                onNavigationNext.invoke()
            }
        )
    }
}