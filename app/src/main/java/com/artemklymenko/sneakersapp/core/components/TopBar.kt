package com.artemklymenko.sneakersapp.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.ui.theme.md_theme_light_primaryContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    menuIcon: ImageVector? = null,
    onMenuClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        colors = TopAppBarDefaults.topAppBarColors(md_theme_light_primaryContainer),
        navigationIcon = {
            onBackClick?.let{
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button))
                }
            }
        },
        actions = {
            menuIcon?.let {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = stringResource(id = R.string.menu_button))
                }
            }
        }
    )
}

@Composable
fun TopBarAsText(title: String) {
    Text(
        text = title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview
@Composable
private fun TopBarWithTitleOnlyPreview() {
    TopBar(
        title = "TopBar with title only",
        onBackClick = null,
        menuIcon = null,
        onMenuClick = {}
    )
}

@Preview
@Composable
private fun TopBarWithTitleAndBackButtonPreview() {
    TopBar(
        title = "TopBar with title and back button",
        onBackClick = {},
        menuIcon = null,
        onMenuClick = {}
    )
}

@Preview
@Composable
private fun TopBarWithTitleAndMenuPreview() {
    TopBar(
        title = "TopBar with title and menu",
        onBackClick = null,
        menuIcon = Icons.Filled.Check,
        onMenuClick = {}
    )
}

@Preview
@Composable
private fun TopBarWithTitleAndBackButtonAndMenuPreview() {
    TopBar(
        title = "TopBar with title, back button, and menu",
        onBackClick = {},
        menuIcon = Icons.Filled.Check,
        onMenuClick = {}
    )
}

