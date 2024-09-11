package com.artemklymenko.sneakersapp.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.artemklymenko.sneakersapp.navigation.RootAppNavigation
import com.artemklymenko.sneakersapp.ui.theme.SneakersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            SneakersAppTheme(darkTheme = isDarkTheme) {
                RootAppNavigation(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { isDarkTheme = !isDarkTheme }
                )
            }
        }
    }
}
