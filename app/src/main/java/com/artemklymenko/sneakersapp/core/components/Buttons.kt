package com.artemklymenko.sneakersapp.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = onClick,
            enabled = isEnabled
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            border = BorderStroke(2.dp, Color.Blue),
            shape = RoundedCornerShape(8.dp),
            enabled = isEnabled,
            onClick = onClick
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Box(modifier = modifier){
        FloatingActionButton(
            onClick = onClick,
            elevation = elevation
        ) {
            icon.invoke()
        }
    }
}

@Composable
fun SquareButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(),
    paddingValues: Dp,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Box(modifier = modifier){
        Button(
            modifier = Modifier,
            elevation = elevation,
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(paddingValues),
            enabled = isEnabled,
            onClick = onClick
        ) {
            icon.invoke()
        }
    }
}

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(modifier = modifier){
        Button(
            modifier = Modifier.height(28.dp),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(2.dp),
            enabled = isEnabled,
            onClick = onClick
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButton(
            modifier = Modifier,
            text = "Primary Button",
            isEnabled = true,
            onClick = {}
        )
        PrimaryButton(
            modifier = Modifier,
            text = "Primary Button",
            isEnabled = false,
            onClick = {}
        )
        
        SecondaryButton(
            modifier = Modifier,
            text = "Secondary Button",
            isEnabled = true,
            onClick = {}
        )
        SecondaryButton(
            modifier = Modifier,
            text = "Secondary Button",
            isEnabled = false,
            onClick = {}
        )

        CircleButton(
            modifier = Modifier,
            icon = {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add button")
            },
            onClick = {}
        )
        
        SquareButton(
            modifier = Modifier,
            icon = {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "notifications")
            },
            isEnabled = true,
            paddingValues = 8.dp,
            onClick = {}
        )

        SmallButton(
            modifier = Modifier,
            text = "Small button enabled",
            isEnabled = true,
            onClick = {}
        )
        SmallButton(
            modifier = Modifier,
            text = "Small button disabled",
            isEnabled = false,
            onClick = {}
        )
    }
}