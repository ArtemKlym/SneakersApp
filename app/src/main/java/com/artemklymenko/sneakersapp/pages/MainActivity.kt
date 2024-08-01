package com.artemklymenko.sneakersapp.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.artemklymenko.sneakersapp.navigation.RootAppNavigation
import com.artemklymenko.sneakersapp.ui.theme.SneakersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SneakersAppTheme {
                RootAppNavigation()
            }
        }
    }
}
