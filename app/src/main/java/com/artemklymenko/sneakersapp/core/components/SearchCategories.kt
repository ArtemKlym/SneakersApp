package com.artemklymenko.sneakersapp.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.utils.MockUtils

@Composable
fun SearchCategories(
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf(tags.first()) }

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tags){ tag ->
            SearchTag(
                tag = tag,
                isSelected = tag == selectedCategory,
                onClick = { selectedCategory = tag }
            )
        }
    }
}

@Composable
private fun SearchTag(
    tag: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondaryContainer
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = tag,
            textAlign = TextAlign.Center,
            color = if (isSelected) MaterialTheme.colorScheme.background
            else MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.width(48.dp)
        )
    }
}

@Preview
@Composable
private fun SearchCategoriesPreview() {
    SearchCategories(tags = MockUtils.loadMockSearchCategories())
}