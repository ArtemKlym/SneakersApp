package com.artemklymenko.sneakersapp.pages.main.pages.profile

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
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateToSettings: () -> Unit,
    onNavigateToPersonalDetails: () -> Unit,
    onNavigateToDeliveryAddress: () -> Unit,
    onNavigateToBillingDetails: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = "Profile")
        },
        content = {
            ProfileScreenContent(
                modifier = Modifier.padding(it),
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToPersonalDetails = onNavigateToPersonalDetails,
                onNavigateToDeliveryAddress = onNavigateToDeliveryAddress,
                onNavigateToBillingDetails = onNavigateToBillingDetails
            )
        }
    )
}

@Composable
private fun ProfileScreenContent(
    modifier: Modifier,
    onNavigateToSettings: () -> Unit,
    onNavigateToPersonalDetails: () -> Unit,
    onNavigateToDeliveryAddress: () -> Unit,
    onNavigateToBillingDetails: () -> Unit
) {
    Column(modifier = modifier) {
        Text(text = "This is profile screen", fontSize = 22.sp, color = Color.Red)
        Text(
            text = "To settings",
            modifier.clickable { onNavigateToSettings.invoke() }
        )
        Text(
            text = "To personal details",
            modifier.clickable { onNavigateToPersonalDetails.invoke() }
        )
        Text(
            text = "To delivery address",
            modifier.clickable { onNavigateToDeliveryAddress.invoke() }
        )
        Text(
            text = "To billing details",
            modifier.clickable { onNavigateToBillingDetails.invoke() }
        )
    }
}
