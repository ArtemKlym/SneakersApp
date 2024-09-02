package com.artemklymenko.sneakersapp.pages.checkout

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.NavigationTitle
import com.artemklymenko.sneakersapp.core.components.PrimaryButton
import com.artemklymenko.sneakersapp.core.components.ProductCategory
import com.artemklymenko.sneakersapp.core.components.ProductDescription
import com.artemklymenko.sneakersapp.core.components.ProductImage
import com.artemklymenko.sneakersapp.core.components.ProductSquareImage
import com.artemklymenko.sneakersapp.core.components.ProductTitle
import com.artemklymenko.sneakersapp.core.components.TopBarAsText
import com.artemklymenko.sneakersapp.domain.models.ProductCart
import com.artemklymenko.sneakersapp.utils.MockUtils

@Composable
fun CheckoutScreen(
    viewModel: CheckoutViewModel,
    navigateToDeliveryAddresses: () -> Unit,
    navigateToPaymentMethods: () -> Unit,
    navigateToProduct: (Long) -> Unit,
    navigateToConfirmationScreen: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            CheckoutUiEvent.LoadScreenData
        )
    }
    BaseContentLayout(viewModel = viewModel) { uiState ->
        uiState?.let {
            CheckoutScreenContent(
                uiState = it,
                uiEvent = viewModel::handleUiEvent,
                navigateToDeliveryAddresses = navigateToDeliveryAddresses,
                navigateToProduct = navigateToProduct,
                navigateToPaymentMethods = navigateToPaymentMethods,
                navigateToConfirmationScreen = navigateToConfirmationScreen
            )
        }
    }
}

@Composable
private fun CheckoutScreenContent(
    uiState: CheckoutUiState,
    uiEvent: (CheckoutUiEvent) -> Unit,
    navigateToDeliveryAddresses: () -> Unit,
    navigateToProduct: (Long) -> Unit,
    navigateToPaymentMethods: () -> Unit,
    navigateToConfirmationScreen: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarAsText(title = stringResource(id = R.string.label_checkout))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            NavigationTitle(title = stringResource(id = R.string.label_delivery_address)) {
                navigateToDeliveryAddresses()
            }
            LazyColumn(modifier = Modifier.weight(0.25f)) {
                items(uiState.addresses) { address ->
                    CheckoutListItem(
                        icon = Icons.Default.Home,
                        iconSize = 48.dp,
                        title = address.name,
                        description = address.address,
                        isSelected = address.isSelected,
                        onClick = {
                            uiEvent(CheckoutUiEvent.SelectDeliveryAddress(address.id))
                        }
                    )
                }
            }

            NavigationTitle(title = stringResource(id = R.string.label_payment_methods)) {
                navigateToPaymentMethods()
            }
            LazyColumn(modifier = Modifier.weight(0.25f)) {
                items(uiState.paymentMethods) { paymentMethod ->
                    CheckoutListItem(
                        icon = Icons.Default.Home,
                        iconSize = 48.dp,
                        title = paymentMethod.name,
                        description = paymentMethod.number,
                        isSelected = paymentMethod.isSelected,
                        onClick = {
                            uiEvent(CheckoutUiEvent.SelectPaymentMethod(paymentMethod.id))
                        }
                    )
                }
            }

            NavigationTitle(title = stringResource(id = R.string.label_my_cart)) {
                navigateToPaymentMethods()
            }
            LazyRow(
                modifier = Modifier.weight(0.25f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(uiState.products) { product ->
                    HorizontalProductItem(
                        product = product
                    ) {
                        navigateToProduct(product.id)
                    }
                }

            }

            Box(
                modifier = Modifier
                    .weight(0.15f)
                    .padding(16.dp)
            ) {
                PrimaryButton(text = stringResource(id = R.string.pay_now)) {
                    navigateToConfirmationScreen()
                }
            }
        }
    }
}

@Composable
private fun HorizontalProductItem(
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

@Composable
private fun CheckoutListItem(
    icon: ImageVector,
    iconSize: Dp,
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        ProductImage(modifier = Modifier, res = icon, size = iconSize)

        Column(modifier = Modifier.padding(start = 8.dp)) {
            ProductTitle(title = title)
            ProductDescription(description = description, maxLines = 1)
        }

        Spacer(modifier = Modifier.weight(1f))

        RadioButton(selected = isSelected, onClick = { onClick() })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CheckoutScreenContentPreview() {
    CheckoutScreenContent(
        uiState = CheckoutUiState(
            addresses = MockUtils.loadMockAddresses(),
            paymentMethods = MockUtils.loadMockPaymentMethods(),
            products = MockUtils.loadMockCart(),
            total = 0.0

        ),
        uiEvent = {},
        navigateToDeliveryAddresses = {},
        navigateToPaymentMethods = {},
        navigateToProduct = {},
        navigateToConfirmationScreen = {},
    )
}