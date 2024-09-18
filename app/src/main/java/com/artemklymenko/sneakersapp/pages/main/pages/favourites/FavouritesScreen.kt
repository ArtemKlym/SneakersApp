package com.artemklymenko.sneakersapp.pages.main.pages.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.ProductDescription
import com.artemklymenko.sneakersapp.core.components.ProductImage
import com.artemklymenko.sneakersapp.core.components.ProductPrice
import com.artemklymenko.sneakersapp.core.components.ProductTitle
import com.artemklymenko.sneakersapp.core.components.TopBarAsText
import com.artemklymenko.sneakersapp.domain.models.local.ProductDetails
import com.artemklymenko.sneakersapp.utils.MockUtils

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel,
    onNavigateToProduct: (Long) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            FavouritesUiEvent.Initiate
        )
    }
    BaseContentLayout(viewModel = viewModel) { uiState ->
        uiState?.let {
            FavouritesScreenContent(
                products = it.items,
                event = viewModel::handleUiEvent,
                onNavigateToProduct = onNavigateToProduct
            )
        }
    }
}

@Composable
private fun FavouritesScreenContent(
    products: List<ProductDetails>,
    event: (FavouritesUiEvent) -> Unit,
    onNavigateToProduct: (Long) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarAsText(title = stringResource(id = R.string.label_favourites))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(products){ product ->
                ProductFavouriteCard(
                    product = product,
                    event = event,
                    onNavigateToProduct = onNavigateToProduct
                )
            }
        }
    }
}

@Composable
fun ProductFavouriteCard(
    product: ProductDetails,
    event: (FavouritesUiEvent) -> Unit,
    onNavigateToProduct: (Long) -> Unit
) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductImage(
                url = product.imageUrls.first(),
                id = product.id,
                onNavigateToProduct = onNavigateToProduct
            )

            Column(
                modifier = Modifier.padding(start = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ProductTitle(title = product.title)
                    ProductPrice(price = product.price)
                    DeleteButton(id = product.id, event = event)
                }
                ProductDescription(description = product.description, maxLines = 3)
            }
        }
    }
}

@Composable
private fun DeleteButton(
    id: Long,
    event: (FavouritesUiEvent) -> Unit
) {
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedIconButton(
            modifier = Modifier.size(22.dp),
            onClick = { event(FavouritesUiEvent.OnItemDeleted(id)) },
            content = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.surfaceTint
                )
            }
        )
    }
}

@Preview
@Composable
private fun FavouritesScreenContentPreview() {
    FavouritesScreenContent(
        products = MockUtils.loadMockFavourites(),
        event = {},
        onNavigateToProduct = {}
    )
}


