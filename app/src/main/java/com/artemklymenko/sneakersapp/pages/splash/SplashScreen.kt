package com.artemklymenko.sneakersapp.pages.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.ProgressIndicator
import com.artemklymenko.sneakersapp.domain.models.network.auth.User

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onNavigationWelcome: () -> Unit,
    onNavigationMain: (User) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            SplashUiEvent.LoadUserInfo
        )
    }
    BaseContentLayout(viewModel = viewModel) { uiState ->
        Scaffold(
            content = { padding ->
                SplashScreenContent(
                    modifier = Modifier.padding(padding)
                )
            }
        )
        uiState?.let {
            if(it.hasToken){
                onNavigationMain(it.user!!)
            }else{
                onNavigationWelcome()
            }
        }
    }
}

@Composable
private fun SplashScreenContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
           ProgressIndicator()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenContentPreview() {
    SplashScreenContent(modifier = Modifier)
}