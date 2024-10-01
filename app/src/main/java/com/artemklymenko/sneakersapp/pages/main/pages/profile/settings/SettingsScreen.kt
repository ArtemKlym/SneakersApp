package com.artemklymenko.sneakersapp.pages.main.pages.profile.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.InsertDriveFile
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Cookie
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.components.TopBarAsText

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit
) {
    SettingsScreenContent(
        modifier = Modifier,
        isDarkTheme = isDarkTheme,
        onThemeChange = onThemeChange
    )
    BackHandler {
        onBackClick()
    }
}

@Composable
private fun SettingsScreenContent(
    modifier: Modifier,
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit
) {
    var notification by remember { mutableStateOf(false) }
    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TopBarAsText(title = stringResource(id = R.string.settings))
            SettingsItem(
                title = stringResource(id = R.string.notifications),
                checked = notification,
                icon = Icons.Outlined.Notifications,
                onClick = {
                    notification = !notification
                }
            )
            SettingsItem(
                title = stringResource(R.string.dark_mode),
                checked = isDarkTheme,
                icon = Icons.Default.Brightness6,
                onClick = {
                    onThemeChange()
                }
            )
            SettingsItem(
                title = stringResource(R.string.rate_app),
                icon = Icons.Outlined.Star
            )
            SettingsItem(
                title = stringResource(R.string.share_app),
                icon = Icons.Outlined.Share
            )
            SettingsItem(
                title = stringResource(id = R.string.privacy_policy),
                icon = Icons.Outlined.Lock
            )
            SettingsItem(
                title = stringResource(id = R.string.terms_conditions),
                icon = Icons.AutoMirrored.Outlined.InsertDriveFile
            )
            SettingsItem(
                title = stringResource(R.string.cookies_policy),
                icon = Icons.Outlined.Cookie
            )
            SettingsItem(
                title = stringResource(R.string.contact),
                icon = Icons.Outlined.Mail
            )
            SettingsItem(
                title = stringResource(R.string.feedback),
                icon = Icons.Outlined.ChatBubbleOutline
            )
        }
    }
}

@Composable
private fun SettingsItem(
    title: String,
    checked: Boolean? = null,
    icon: ImageVector,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = icon,
                contentDescription = title,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.W400
            )
        }
        checked?.let {
            Switch(
                checked = checked,
                onCheckedChange = {
                    onClick()
                }
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(
        onBackClick = {},
        isDarkTheme = false
    ) {}
}