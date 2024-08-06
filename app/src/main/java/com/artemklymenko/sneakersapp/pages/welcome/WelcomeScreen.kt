package com.artemklymenko.sneakersapp.pages.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.components.PrimaryButton

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel,
    onNavigationToSignIn: () -> Unit,
    onNavigationToSignUp: () -> Unit
) {
    Scaffold(
        content = {
            WelcomeScreenContent(
                modifier = Modifier.padding(it)
            )
        },
        bottomBar = {
            Box(modifier = Modifier.padding(16.dp)) {
                PrimaryButton(
                    text = stringResource(id = R.string.next),
                    onClick = onNavigationToSignIn
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreenContent(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        val pageCount = 5
        val pagerState = rememberPagerState(0, 0f){
            pageCount
        }
        HorizontalPager(
            modifier = modifier.border(1.dp, Color.Black),
            state = pagerState
        ) {
            Box(modifier.fillMaxSize()){
                Text(text = it.toString())
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount){ interaction ->
                val color =
                if(pagerState.currentPage == interaction) Color.Gray else Color.Gray.copy(0.5f)
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun WelcomeScreenContentPreview() {
    WelcomeScreenContent(
        modifier = Modifier,
    )
}