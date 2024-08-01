package com.artemklymenko.sneakersapp.pages.welcome

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
fun WelcomeScreen(
    viewModel: WelcomeViewModel,
    onNavigationToSignIn: () -> Unit,
    onNavigationToSignUp: () -> Unit
) {
    Scaffold(
        topBar = { TopBar() },
        content = {
            WelcomeScreenContent(
                modifier = Modifier.padding(it),
                onNavigationToSignIn = onNavigationToSignIn,
                onNavigationToSignUp = onNavigationToSignUp
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
                text = "Welcome",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}

@Composable
private fun WelcomeScreenContent(
    modifier: Modifier,
    onNavigationToSignIn: () -> Unit,
    onNavigationToSignUp: () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Navigate to Sign In",
            modifier = modifier.clickable {
                onNavigationToSignIn.invoke()
            }
        )
        Text(
            text = "Navigate to Sign Up",
            modifier = modifier.clickable {
                onNavigationToSignUp.invoke()
            }
        )
    }
}