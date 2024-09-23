package com.artemklymenko.sneakersapp.pages.main.pages.feed

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.SearchBar
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.SearchCategories
import com.artemklymenko.sneakersapp.core.components.TopBarAsText
import com.artemklymenko.sneakersapp.domain.models.network.product.Product

@Composable
fun FeedScreen(
    viewModel: FeedViewModel,
    onNavigateToNotifications: () -> Unit,
    onNavigateToProduct: (Int) -> Unit
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
        uiState?.let { state ->
            FeedScreenContent(
                uiState = state,
                searchCategories = state.searchCategories,
                onNavigateToProduct = onNavigateToProduct,
                onNavigateToNotifications = onNavigateToNotifications,
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun FeedScreenContent(
    searchCategories: List<String>,
    onNavigateToProduct: (Int) -> Unit,
    onNavigateToNotifications: () -> Unit,
    uiState: FeedUiState,
    viewModel: FeedViewModel
) {
    Column {
        TopBarAsText(title = stringResource(id = R.string.label_feed))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SearchBarView(uiState, viewModel, onNavigateToProduct)
            NotificationImageButton(onNavigateToNotifications)
        }
        uiState.let { state ->
            SearchCategories(
                tags = searchCategories,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                onTagSelected = { selectedTag ->
                    viewModel.handleUiEvent(FeedUiEvent.SortProducts(selectedTag))
                }
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                columns = GridCells.Fixed(2)
            ) {
                items(state.filteredProducts.size) { index ->
                    ProductItem(
                        product = state.filteredProducts[index],
                        onNavigateToProduct = onNavigateToProduct
                    )
                }
            }
        }
    }
}

@Composable
private fun NotificationImageButton(onNavigateToNotifications: () -> Unit) {
    IconButton(
        modifier = Modifier
            .size(48.dp)
            .padding(end = 4.dp, top = 24.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        onClick = {
            onNavigateToNotifications()
        }) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.Notifications,
            contentDescription = stringResource(R.string.notifications),
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchBarView(
    uiState: FeedUiState,
    viewModel: FeedViewModel,
    onNavigateToProduct: (Int) -> Unit
) {
    var active by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    BackHandler(enabled = active) {
        active = false
        focusManager.clearFocus()
    }
    SearchBar(
        query = uiState.searchQuery,
        onQueryChange = { query ->
            viewModel.handleUiEvent(FeedUiEvent.OnSearchQueryChange(query))
        },
        onSearch = {
            viewModel.onSearch(uiState.searchQuery)
        },
        active = active,
        onActiveChange = { newActive ->
            active = newActive
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search)
            )
        },
        placeholder = {
            Text(text = stringResource(R.string.search))
        },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(16.dp))
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    if (active) {
                        active = false
                        focusManager.clearFocus()
                    }
                })
            }
    ) {
        LazyColumn {
            items(uiState.filteredProducts) { product ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = rememberAsyncImagePainter(model = product.images[0]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = product.title,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .clickable {
                                onNavigateToProduct(product.id)
                            }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductItem(
    product: Product,
    onNavigateToProduct: (Int) -> Unit
) {
    var isFavourite by remember { mutableStateOf(false) }
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
                    painter = rememberAsyncImagePainter(model = product.images[0]),
                    contentDescription = null,
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
//    FeedScreenContent(
//        uiState = FeedUiState(),
//        products = MockUtils.loadMockProducts(),
//        searchCategories = MockUtils.loadMockSearchCategories(),
//        onNavigateToProduct = {},
//        onNavigateToNotifications = {},
//        viewModel = FeedViewModel()
//    )
}
