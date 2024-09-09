package com.artemklymenko.sneakersapp.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.artemklymenko.sneakersapp.R

@Composable
fun ThreeDotMenu(onEdit: () -> Unit, onDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.menu)
        )
    }
    DropdownMenu(
        expanded = expanded,
        offset = DpOffset(x = (-40).dp, y = 0.dp),
        onDismissRequest = { expanded = false },
        properties = PopupProperties(
            focusable = true
        )
    ) {
        DropdownMenuItem(
            text = { Text(stringResource(R.string.edit)) },
            onClick = {
                expanded = false
                onEdit()
            }
        )
        DropdownMenuItem(
            text = { Text(stringResource(R.string.delete)) },
            onClick = {
                expanded = false
                onDelete()
            }
        )
    }
}