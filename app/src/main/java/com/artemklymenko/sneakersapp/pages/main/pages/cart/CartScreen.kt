package com.artemklymenko.sneakersapp.pages.main.pages.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.PrimaryButton
import com.artemklymenko.sneakersapp.core.components.ProductCategory
import com.artemklymenko.sneakersapp.core.components.ProductPrice
import com.artemklymenko.sneakersapp.core.components.ProductSquareImage
import com.artemklymenko.sneakersapp.core.components.ProductTitle
import com.artemklymenko.sneakersapp.core.components.TopBarAsText
import com.artemklymenko.sneakersapp.domain.models.local.ProductCart

@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onNavigateToPromoCode: () -> Unit,
    onNavigateToCheckout: () -> Unit,
    onNavigateToProduct: (Long) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            CartUiEvent.LoadScreenData
        )
    }

    BaseContentLayout(viewModel = viewModel) { uiState ->
        uiState?.let {
            CartScreenContent(
                uiState = it,
                uiEvent = viewModel::handleUiEvent,
                onNavigateToProduct = onNavigateToProduct,
                onNavigateToPromoCode = onNavigateToPromoCode,
                onNavigateToCheckout = onNavigateToCheckout,
            )
        }
    }
}

@Composable
private fun CartScreenContent(
    uiState: CartUiState,
    uiEvent: (CartUiEvent) -> Unit,
    onNavigateToPromoCode: () -> Unit,
    onNavigateToCheckout: () -> Unit,
    onNavigateToProduct: (Long) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(0.5f)) {
            TopBarAsText(stringResource(R.string.label_cart))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.products) { product ->
                    CartProductItem(
                        product = product,
                        uiEvent = uiEvent,
                        onNavigateToProduct = onNavigateToProduct,
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(0.5f)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                LineDivider()
                PromoTitle(onNavigateToPromoCode)
                PriceDetailsRow(stringResource(R.string.shipping), uiState.shipping)
                PriceDetailsRow(stringResource(R.string.subtotal), uiState.subtotal)
                PriceDetailsRow(stringResource(R.string.shipping), uiState.shipping)
                PriceDetailsRow(stringResource(R.string.discount), uiState.discount)
                LineDivider()
                PriceDetailsRow(stringResource(R.string.total), uiState.total)
            }
            Column {
                Box(
                    modifier = Modifier.padding(bottom = 16.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    PrimaryButton(
                        text = stringResource(id = R.string.checkout),
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    ) {
                        onNavigateToCheckout()
                    }
                }
            }

        }
    }
}

@Composable
private fun CartProductItem(
    product: ProductCart,
    uiEvent: (CartUiEvent) -> Unit,
    onNavigateToProduct: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onNavigateToProduct.invoke(product.id) },
                contentAlignment = Alignment.Center
            ) {
                ProductSquareImage(imageUrl = product.imageUrl)
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                ProductTitle(title = product.title)
                ProductCategory(category = product.category)
                ProductPrice(price = product.price)
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.remove_circle),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            uiEvent(CartUiEvent.OnDecreaseQuantity(product.id))
                        }
                    )
                    Text(text = product.quantity.toString())
                    Image(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            uiEvent(CartUiEvent.OnIncreaseQuantity(product.id))
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LineDivider() {
    Box(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(Color.Gray.copy(alpha = 0.25f))
    )
}

@Composable
private fun PriceDetailsRow(title: String, price: Double) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        ProductPrice(
            price = price
        )
    }
}

@Composable
private fun PromoTitle(onNavigateToPromoCode: () -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigateToPromoCode()
            }) {
        Text(
            text = stringResource(id = R.string.promo_code),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(16.dp)
        )
        Image(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}