package com.artemklymenko.sneakersapp.pages.confirm

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.HorizontalProductItem
import com.artemklymenko.sneakersapp.core.components.PrimaryButton
import com.artemklymenko.sneakersapp.core.components.ProductDescription
import com.artemklymenko.sneakersapp.core.components.ProductImage
import com.artemklymenko.sneakersapp.core.components.ProductTitle
import com.artemklymenko.sneakersapp.core.components.TopBarAsText
import com.artemklymenko.sneakersapp.domain.models.local.DeliveryAddress
import com.artemklymenko.sneakersapp.domain.models.local.PaymentMethod
import com.artemklymenko.sneakersapp.domain.models.local.ProductCart
import com.artemklymenko.sneakersapp.utils.MockUtils

@Composable
fun ConfirmScreen(
    viewModel: ConfirmViewModel,
    onNavigateToSuccess: () -> Unit,
    onBackClick: () -> Unit,
    paymentId: Long,
    addressId: Long
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            ConfirmUiEvent.LoadData(paymentId, addressId)
        )
    }
    BaseContentLayout(viewModel = viewModel) { uiState ->
        uiState?.let {
            ConfirmContent(
                address = uiState.address,
                paymentMethod = uiState.paymentMethod,
                products = uiState.products,
                total = uiState.total
            ) {
                onNavigateToSuccess()
            }
        }
    }
    BackHandler {
        onBackClick.invoke()
    }
}

@Composable
private fun ConfirmContent(
    address: DeliveryAddress,
    paymentMethod: PaymentMethod,
    products: List<ProductCart>,
    total: Double,
    onNavigateToSuccess: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopBarAsText(title = stringResource(id = R.string.confirmation))
        Text(
            text = stringResource(id = R.string.label_delivery_address),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp)
        )
        ConfirmItem(
            icon = Icons.Default.Home,
            iconSize = 48.dp,
            title = address.name,
            description = address.address
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.label_payment_methods),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp)
        )
        ConfirmItem(
            icon = Icons.Filled.CreditCard,
            iconSize = 48.dp,
            title = paymentMethod.name,
            description = paymentMethod.number
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.weight(0.2f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(products) { product ->
                HorizontalProductItem(product = product) {}
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.total) + ":",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "$$total",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        PrimaryButton(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = stringResource(id = R.string.confirm)
        ) {
            onNavigateToSuccess.invoke()
        }
    }
}

@Composable
private fun ConfirmItem(
    icon: ImageVector,
    iconSize: Dp,
    title: String,
    description: String
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
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmScreenContent() {
    ConfirmContent(
        address = DeliveryAddress(
            id = 1,
            name = "Work",
            address = "Lviv, Ukraine, 03559",
            isSelected = false
        ),
        paymentMethod = PaymentMethod(
            id = 1,
            name = "Visa",
            number = "**** **** **** 1234",
            isSelected = true
        ),
        products = MockUtils.loadMockCart(),
        total = 112.33
    ) {

    }
}
