package com.artemklymenko.sneakersapp.pages.main.pages.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.components.TopBarAsText
import com.artemklymenko.sneakersapp.domain.models.network.auth.User

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateToSettings: () -> Unit,
    onNavigateToPersonalDetails: () -> Unit,
    onNavigateToDeliveryAddress: () -> Unit,
    onNavigateToBillingDetails: () -> Unit,
    onNavigateToLogOut: () -> Unit,
    user: User,
) {
    ProfileScreenContent(
        onNavigateToSettings = onNavigateToSettings,
        onNavigateToPersonalDetails = onNavigateToPersonalDetails,
        onNavigateToDeliveryAddress = onNavigateToDeliveryAddress,
        onNavigateToBillingDetails = onNavigateToBillingDetails,
        user = user,
        onNavigateToLogOut = onNavigateToLogOut
    )
    //Local
//    LaunchedEffect(key1 = Unit) {
//        viewModel.handleUiEvent(
//            ProfileUiEvent.LoadData
//        )
//    }
//    BaseContentLayout(viewModel = viewModel) { uiState ->
//        uiState?.let {
//            ProfileScreenContent(
//                onNavigateToSettings = onNavigateToSettings,
//                onNavigateToPersonalDetails = onNavigateToPersonalDetails,
//                onNavigateToDeliveryAddress = onNavigateToDeliveryAddress,
//                onNavigateToBillingDetails = onNavigateToBillingDetails,
//                onNavigateToLogOut = onNavigateToLogOut,
//                user = it
//            )
//        }
//    }
}

@Composable
private fun ProfileScreenContent(
    onNavigateToSettings: () -> Unit,
    onNavigateToPersonalDetails: () -> Unit,
    onNavigateToDeliveryAddress: () -> Unit,
    onNavigateToBillingDetails: () -> Unit,
    //user: ProfileUiState, local
    user: User,
    onNavigateToLogOut: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarAsText(title = stringResource(id = R.string.label_profile))
        Image(
            painter = rememberAsyncImagePainter(model = user.image),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(240.dp)
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(24.dp)),
        )
        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = user.firstName + " " + user.lastName,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            maxLines = 2,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = user.email,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            maxLines = 2,
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RectangleShape
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            ProfileItem(
                icon = Icons.Default.Settings,
                title = stringResource(R.string.settings),
                onClick = onNavigateToSettings
            )
            ProfileItem(
                icon = Icons.Filled.Done,
                title = stringResource(R.string.billing_details),
                onClick = onNavigateToBillingDetails
            )
            ProfileItem(
                icon = Icons.Default.Home,
                title = stringResource(R.string.delivery_address_details),
                onClick = onNavigateToDeliveryAddress
            )
            ProfileItem(
                icon = Icons.Default.Person,
                title = stringResource(R.string.personal_data_details),
                onClick = onNavigateToPersonalDetails
            )
            ProfileItem(
                icon = Icons.AutoMirrored.Default.ExitToApp,
                title = stringResource(R.string.log_out),
                onClick = onNavigateToLogOut
            )
        }

    }

}

@Composable
private fun ProfileItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(32.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(8.dp),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    imageVector = icon,
                    contentDescription = title,
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                modifier = Modifier
                    .clickable {
                        onClick.invoke()
                    },
                text = title,
            )
        }
        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onClick.invoke()
                },
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenContentPreview() {
    ProfileScreenContent(
        onNavigateToSettings = {  },
        onNavigateToPersonalDetails = {  },
        onNavigateToDeliveryAddress = {  },
        onNavigateToBillingDetails = {  },
//        user = ProfileUiState(
//            id = 1,
//            name = "Jack",
//            surname = "London",
//            email = "jack.london@gmail.com",
//            urlImage = "https://hips.hearstapps.com/hmg-prod/images/gettyimages-3346353-copy.jpg"
//        )
        user = User(
            id = 1,
            username = "username",
            email =  "email@test.com",
            firstName = "Jack",
            lastName = "London",
            gender = "male",
            image = "https://hips.hearstapps.com/hmg-prod/images/gettyimages-3346353-copy.jpg",
            accessToken = "",
            refreshToken = ""
        )
    ) {

    }
}
