package com.artemklymenko.sneakersapp.pages.promo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.components.PrimaryButton
import com.artemklymenko.sneakersapp.ui.theme.md_theme_dark_onPrimary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PromoScreen(
    viewModel: PromoViewModel
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        content = {
            PromoScreenContent(
                modifier = Modifier.padding(it),
                snackBarHostState = snackBarHostState,
                scope = scope
            )
        }
    )
}

@Composable
private fun PromoScreenContent(
    modifier: Modifier,
    snackBarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember { mutableStateOf("") }
        Column(
            modifier = modifier.weight(2f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.enter_your_promo_code),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = md_theme_dark_onPrimary,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 48.sp,
                modifier = modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                value = text,
                onValueChange = { text = it },
                label = {
                    Text(text = stringResource(id = R.string.promo_code))
                }
            )
        }
        PrimaryButton(
            text = stringResource(R.string.submit),
            modifier = Modifier
                .weight(0.25f)
                .padding(horizontal = 16.dp)
        ) {
            if(text.isNotEmpty()) {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = "Promo code activated",
                        actionLabel = "Confirm",
                        duration = SnackbarDuration.Short
                    )
                }
                text = ""
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PromoScreenPreview(){
    PromoScreen(viewModel = PromoViewModel())
}