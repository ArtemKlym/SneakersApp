package com.artemklymenko.sneakersapp.pages.welcome

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.components.ProductDescription

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel,
    onNavigationToSignIn: () -> Unit,
    onNavigationToSignUp: () -> Unit
) {
    Scaffold {
        WelcomeScreenContent(
            modifier = Modifier.padding(it),
            onNavigationToSignIn = onNavigationToSignIn,
            onNavigationToSignUp = onNavigationToSignUp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreenContent(
    modifier: Modifier,
    onNavigationToSignIn: () -> Unit,
    onNavigationToSignUp: () -> Unit
) {
    val pageCount = 3
    val pagerState = rememberPagerState(0, 0f) {
        pageCount
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HorizontalPager(
            state = pagerState
        ) { page ->
            when (page) {
                0 -> PageContent(
                    title = stringResource(R.string.discover_products),
                    description = stringResource(R.string.find_your_favourite_sneakers),
                    icon = Icons.Filled.AddShoppingCart,
                )

                1 -> PageContent(
                    title = stringResource(R.string.ease_safe_payment),
                    description = stringResource(R.string.pay_for_the_sneakers_you_buy_safely_easily),
                    icon = Icons.Filled.CreditCard,
                )

                2 -> PageContent(
                    title = stringResource(R.string.sign_in_or_sign_up),
                    icon = Icons.AutoMirrored.Filled.Login,
                    buttonText = stringResource(R.string.sign_in),
                    onFirstButtonClick = onNavigationToSignIn,
                    secondaryButtonText = stringResource(R.string.sign_up),
                    onSecondaryButtonClick = onNavigationToSignUp
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { interaction ->
                val isSelected = pagerState.currentPage == interaction
                val color by animateColorAsState(
                    targetValue = if (isSelected) Color.Gray else Color.Gray.copy(0.5f),
                    animationSpec = tween(durationMillis = 300),
                    label = ""
                )
                val width by animateDpAsState(
                    targetValue = if (isSelected) 32.dp else 8.dp,
                    animationSpec = tween(durationMillis = 300),
                    label = ""
                )
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(height = 8.dp, width = width)
                )
            }
        }
    }
}

@Composable
private fun PageContent(
    title: String,
    description: String = "",
    icon: ImageVector,
    buttonText: String? = null,
    onFirstButtonClick: (() -> Unit)? = null,
    secondaryButtonText: String? = null,
    onSecondaryButtonClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)
        )

        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProductDescription(description = description, maxLines = 2)

        buttonText?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = onFirstButtonClick ?: {}
            ) {
                Text(text = it)
            }
        }
        secondaryButtonText?.let {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = onSecondaryButtonClick ?: {}
            ) {
                Text(text = it)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun WelcomeScreenContentPreview() {
    WelcomeScreen(viewModel = WelcomeViewModel(), onNavigationToSignIn = { /*TODO*/ }) {

    }
}