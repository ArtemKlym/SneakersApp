package com.artemklymenko.sneakersapp.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.artemklymenko.sneakersapp.R


@Composable
fun ProductImage(
    url: String,
    onClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick.invoke() },
        alignment = Alignment.Center,
        painter = rememberAsyncImagePainter(model = url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}


@Composable
fun ToolbarMenuIcon(
    modifier: Modifier,
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = { onClick() },
        content = {
            Icon(
                icon,
                stringResource(id = R.string.menu_button)
            )
        }
    )
}

@Composable
fun ProductSquareImage(imageUrl: String) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RectangleShape),
        alignment = Alignment.Center,
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}


@Composable
fun ProductLandscapePhoto(url: String) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(12.dp)),
        alignment = Alignment.Center,
        painter = rememberAsyncImagePainter(model = url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}