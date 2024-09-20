package com.artemklymenko.sneakersapp.pages.product

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.PrimaryButton
import com.artemklymenko.sneakersapp.core.components.RatingBar
import com.artemklymenko.sneakersapp.core.components.RoundedButton
import com.artemklymenko.sneakersapp.domain.models.network.Dimensions
import com.artemklymenko.sneakersapp.domain.models.network.Meta
import com.artemklymenko.sneakersapp.domain.models.network.Product
import com.artemklymenko.sneakersapp.domain.models.network.Review
import com.artemklymenko.sneakersapp.utils.getTimeAgo

@Composable
fun ProductScreen(
    viewModel: ProductViewModel,
    id: Int,
    onBuyNowClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            ProductUiEvent.LoadData(id)
        )
    }
    BaseContentLayout(viewModel = viewModel) { uiState ->
        uiState?.let {
            ProductScreenContent(
                product = it.product,
                isAddedToCart = it.isAddedToCart,
                uiEvent = viewModel::handleUiEvent,
                onBuyNowClick = onBuyNowClick
            )
        }
    }
    BackHandler {
        onBackClick()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductScreenContent(
    product: Product,
    isAddedToCart: Boolean,
    uiEvent: (ProductUiEvent) -> Unit,
    onBuyNowClick: () -> Unit
) {
    var rating by remember { mutableDoubleStateOf(product.rating) }
    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Expanded
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContent(
                product = product,
                uiEvent = uiEvent,
                isAddedToCart = isAddedToCart,
                onBuyNowClick = onBuyNowClick
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPagerWithIndicators(product.images)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.how_would_you_rate_these_sneakers),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
            RatingBar(
                modifier = Modifier.size(48.dp),
                rating = rating,
            ) {
                rating = it
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.padding(it)
            ) {
                items(product.reviews) { review ->
                    ReviewItem(review)
                }
            }
        }
    }
}

@Composable
private fun ReviewItem(review: Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = CircleShape
                    )
                    .padding(8.dp),
                imageVector = Icons.Filled.Person,
                contentDescription = null
            )
            Column {
                Text(
                    text = review.reviewerName,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RatingBar(
                        rating = review.rating.toDouble()
                    ) {}
                    Text(text = getTimeAgo(review.date))
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        ProductDescription(description = review.comment)
    }
}

@Composable
fun BottomSheetContent(
    product: Product,
    uiEvent: (ProductUiEvent) -> Unit,
    isAddedToCart: Boolean,
    onBuyNowClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            ProductTitle(title = product.title)
            Spacer(modifier = Modifier.width(8.dp))
            FavouriteButton(false, uiEvent)
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductCategory(category = product.category)
            ProductPrice(price = product.price)
        }
        Spacer(modifier = Modifier.height(8.dp))
        ProductDescription(description = product.description)
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                RoundedButton(
                    icon = {
                        Icon(
                            imageVector = if (isAddedToCart) {
                                Icons.Outlined.CheckCircle
                            } else {
                                Icons.Filled.ShoppingCart
                            },
                            contentDescription = null
                        )
                    },
                    onClick = { uiEvent(ProductUiEvent.AddToCartOnClick) }
                )
                PrimaryButton(
                    text = stringResource(R.string.buy_now),
                    roundDp = 16.dp,
                    onClick = { onBuyNowClick.invoke() }
                )
            }
        }
    }
}

@Composable
private fun ProductDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 10,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Justify
    )
}

@Composable
private fun ProductCategory(category: String) {
    Text(
        text = category,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun FavouriteButton(isFavourite: Boolean, uiEvent: (ProductUiEvent) -> Unit) {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        OutlinedIconToggleButton(
            checked = isFavourite,
            onCheckedChange = { uiEvent(ProductUiEvent.UpdateFavouriteOnClick) },
            content = {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
private fun ProductPrice(price: Double) {
    Text(
        text = "$" + "%.2f".format(price),
        style = MaterialTheme.typography.headlineSmall,
        maxLines = 1,
        color = MaterialTheme.colorScheme.error,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun ProductTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerWithIndicators(imageUrls: List<String>) {
    val pageCount = imageUrls.size
    val pagerState = rememberPagerState { pageCount }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 20.dp),
                pageSpacing = 10.dp
            ) { page ->
                ProductPhoto(url = imageUrls[page])
            }
            DotIndicator(pageCount, pagerState)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DotIndicator(pageCount: Int, pagerState: PagerState) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        repeat(pageCount) { iteration ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(
                        color = if (pagerState.currentPage == iteration) {
                            Color.Gray
                        } else {
                            Color.Gray.copy(alpha = 0.5f)
                        }
                    )
                    .size(12.dp)
            )
        }
    }
}

@Composable
private fun ProductPhoto(url: String) {
    Image(
        alignment = Alignment.Center,
        painter = rememberAsyncImagePainter(model = url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProductScreenContentPreview() {
    ProductScreenContent(
        product = Product(
            id = 1,
            title = "Essence Mascara Lash Princess",
            description = "A volumizing and lengthening mascara for dramatic lashes.",
            category = "beauty",
            price = 9.99,
            discountPercentage = 7.17,
            rating = 4.94,
            stock = 5,
            tags = listOf("beauty", "mascara"),
            brand = "Essence",
            sku = "RCH45Q1A",
            weight = 2,
            dimensions = Dimensions(width = 23.17, height = 14.43, depth = 28.01),
            warrantyInformation = "1 month warranty",
            shippingInformation = "Ships in 1 month",
            availabilityStatus = "Low Stock",
            reviews = listOf(
                Review(
                    rating = 5,
                    comment = "Very satisfied!",
                    date = "2024-05-23T08:56:21.618Z",
                    reviewerName = "Scarlett Wright",
                    reviewerEmail = "scarlett.wright@x.dummyjson.com"
                )
            ),
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 24,
            meta = Meta(
                createdAt = "2024-05-23T08:56:21.618Z",
                updatedAt = "2024-05-23T08:56:21.618Z",
                barcode = "9164035109868",
                qrCode = "..."
            ),
            thumbnail = "https://example.com/image.png",
            images = listOf("https://example.com/image1.png", "https://example.com/image2.png")
        ),
        isAddedToCart = false,
        uiEvent = {},
        onBuyNowClick = {}
    )
}


