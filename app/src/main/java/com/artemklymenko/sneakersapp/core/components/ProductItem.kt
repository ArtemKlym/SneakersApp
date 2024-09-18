package com.artemklymenko.sneakersapp.core.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.domain.models.local.ProductCart

@Composable
fun HorizontalProductItem(
    product: ProductCart,
    onClick: (Long) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .animateContentSize()
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable { onClick(product.id) }
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp)

    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(16.dp))
        ){
            ProductSquareImage(imageUrl = product.imageUrl)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Top)
                .size(128.dp, 64.dp)
                .padding(horizontal = 8.dp)
        ) {
            ProductTitle(title = product.title)
            ProductCategory(category = product.category)
        }
    }
}