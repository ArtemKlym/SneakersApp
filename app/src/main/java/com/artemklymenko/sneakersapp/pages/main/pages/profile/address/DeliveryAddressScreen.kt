package com.artemklymenko.sneakersapp.pages.main.pages.profile.address

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.PrimaryAlertDialog
import com.artemklymenko.sneakersapp.core.components.ProductDescription
import com.artemklymenko.sneakersapp.core.components.ProductTitle
import com.artemklymenko.sneakersapp.core.components.ThreeDotMenu
import com.artemklymenko.sneakersapp.core.components.TopBarAsText
import com.artemklymenko.sneakersapp.domain.models.DeliveryAddress
import com.artemklymenko.sneakersapp.utils.MockUtils

@Composable
fun DeliveryAddressScreen(
    viewModel: DeliveryAddressViewModel,
    onBackClick: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            DeliveryAddressUiEvent.LoadData
        )
    }
    BaseContentLayout(viewModel = viewModel) { uiState ->
        uiState?.let {
            DeliveryAddressScreenContent(
                addresses = it.addresses
            )
        }
    }
    BackHandler {
        onBackClick()
    }
}

@Composable
private fun DeliveryAddressScreenContent(
    addresses: List<DeliveryAddress>
) {
    var listOfAddresses by remember { mutableStateOf(addresses) }
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true
            }) {
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_address)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TopBarAsText(title = stringResource(id = R.string.label_delivery_address))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                items(listOfAddresses) { address ->
                    DeliveryAddressItem(address) {
                        listOfAddresses = listOfAddresses.toMutableStateList().apply {
                            remove(address)
                        }
                    }
                }
            }
        }
    }
    if (showDialog) {
        AddNewDeliveryAddress(addresses, listOfAddresses) { list ->
            showDialog = false
            listOfAddresses = list
        }
    }
}

@Composable
fun DeliveryAddressItem(
    address: DeliveryAddress,
    onClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var newAddress by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        EditAddressDialog(
            address = address,
            name = name,
            onNameChange = { name = it },
            newAddress = newAddress,
            onAddressChange = { newAddress = it },
            onDismiss = { showDialog = false }
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.Home,
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            ProductTitle(title = name.ifEmpty { address.name })
            ProductDescription(description = newAddress.ifEmpty { address.address }, maxLines = 1)
        }
        Spacer(modifier = Modifier.weight(1f))
        ThreeDotMenu(
            onEdit = { showDialog = true },
            onDelete = { onClick() }
        )
    }
}

@Composable
private fun EditAddressDialog(
    address: DeliveryAddress,
    name: String,
    onNameChange: (String) -> Unit,
    newAddress: String,
    onAddressChange: (String) -> Unit,
    onDismiss: () -> Unit
) {
    PrimaryAlertDialog(
        title = stringResource(R.string.label_delivery_address),
        content = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    placeholder = { Text(text = address.name) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newAddress,
                    onValueChange = onAddressChange,
                    placeholder = { Text(text = address.address) }
                )
            }
        },
        onCancel = onDismiss
    ) {
        onDismiss()
    }
}

@Composable
private fun AddNewDeliveryAddress(
    addresses: List<DeliveryAddress>,
    listOfAddresses: List<DeliveryAddress>,
    onClick: (List<DeliveryAddress>) -> Unit
) {
    var listOfAddresses1 = listOfAddresses
    var newName by remember { mutableStateOf("") }
    var newAddress by remember { mutableStateOf("") }
    PrimaryAlertDialog(
        title = stringResource(R.string.add_address),
        content = {
            Column {
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    placeholder = { Text(addresses[0].name) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newAddress,
                    onValueChange = { newAddress = it },
                    placeholder = { Text(addresses[0].address) }
                )
            }
        },
        onCancel = { onClick(listOfAddresses1) }) {
        if (newName.isNotEmpty() && newAddress.isNotEmpty()) {
            listOfAddresses1 = listOfAddresses1.toMutableList().apply {
                add(
                    DeliveryAddress(
                        id = listOfAddresses1.size.toLong(),
                        name = newName,
                        address = newAddress,
                        isSelected = false
                    )
                )
            }
        }
        onClick(listOfAddresses1)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DeliveryAddressScreenPreview() {
    DeliveryAddressScreenContent(addresses = MockUtils.loadMockAddresses())
}
