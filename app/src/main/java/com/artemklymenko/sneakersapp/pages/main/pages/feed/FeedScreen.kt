package com.artemklymenko.sneakersapp.pages.main.pages.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.SearchCategories
import com.artemklymenko.sneakersapp.core.components.TopBarAsText
import com.artemklymenko.sneakersapp.domain.models.Product
import com.artemklymenko.sneakersapp.utils.MockUtils

@Composable
fun FeedScreen(
    viewModel: FeedViewModel,
    onNavigateToNotifications: () -> Unit,
    onNavigateToProduct: (Long) -> Unit,
    onNavigateToSearch: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            FeedUiEvent.LoadScreenData
        )
    }
    BaseContentLayout(
        viewModel = viewModel,
        onBackPressed = null
    ) { uiState ->
        uiState?.let {
            FeedScreenContent(
                products = it.products,
                searchCategories = it.searchCategories,
                onNavigateToProduct = onNavigateToProduct,
                onNavigateToSearch = onNavigateToSearch,
                onNavigateToNotifications = onNavigateToNotifications
            )
        }
    }
}

@Composable
private fun FeedScreenContent(
    products: List<Product>,
    searchCategories: List<String>,
    onNavigateToProduct: (Long) -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToNotifications: () -> Unit
) {
    Column {
        TopBarAsText(title = stringResource(id = R.string.label_feed))
        SearchCategories(
            tags = searchCategories,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(products.size) { index ->
                ProductItem(
                    product = products[index],
                    onNavigateToProduct = onNavigateToProduct
                )
            }
        }
    }
}

@Composable
private fun ProductItem(
    product: Product,
    onNavigateToProduct: (Long) -> Unit
) {
    var isFavourite by remember { mutableStateOf(product.isFavourite) }
    Column {
        Card(
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToProduct.invoke(product.id) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = product.imageUrl),
                    contentDescription = null,
                    alignment = Alignment.TopEnd,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RectangleShape)
                )

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd)
                ) {
                    OutlinedIconToggleButton(
                        checked = isFavourite,
                        onCheckedChange = { isFavourite = it }
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null
                        )
                    }
                }
            }
        }
        ProductItemTitle(product.title)
        ProductItemPrice(product.price)
    }
}

@Composable
private fun ProductItemPrice(price: Double) {
    Text(
        text = "$" + "%.2f".format(price),
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    )
}

@Composable
private fun ProductItemTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
    )
}

@Preview(showSystemUi = true)
@Composable
private fun FeedScreenPreview() {
    FeedScreenContent(
        products = MockUtils.loadMockProducts(),
        searchCategories = MockUtils.loadMockSearchCategories(),
        onNavigateToProduct = {},
        onNavigateToSearch = {}
    ) {

    }
}
