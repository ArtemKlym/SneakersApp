package com.artemklymenko.sneakersapp.pages.main.pages.profile.billing

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.CreditCard
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.PrimaryAlertDialog
import com.artemklymenko.sneakersapp.core.components.ProductDescription
import com.artemklymenko.sneakersapp.core.components.ProductTitle
import com.artemklymenko.sneakersapp.core.components.ThreeDotMenu
import com.artemklymenko.sneakersapp.core.components.TopBarAsText
import com.artemklymenko.sneakersapp.domain.models.local.PaymentMethod
import com.artemklymenko.sneakersapp.utils.CardExpiryVisualTransformation

@Composable
fun BillingDetailsScreen(
    viewModel: BillingDetailsViewModel,
    onBackClick: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            BillingDetailsUiEvent.LoadBillingDetailsData
        )
    }
    BaseContentLayout(viewModel = viewModel) { uiState ->
        uiState?.let {
            BillingScreenContent(
                paymentMethods = uiState.paymentMethods
            )
        }
    }
    BackHandler {
        onBackClick()
    }
}

@Composable
private fun BillingScreenContent(paymentMethods: List<PaymentMethod>) {
    var listOfPaymentMethods by remember { mutableStateOf(paymentMethods) }
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_payment_method)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TopBarAsText(title = stringResource(R.string.billing_details))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                items(listOfPaymentMethods) { paymentMethod ->
                    PaymentMethodItem(paymentMethod) {
                        listOfPaymentMethods = listOfPaymentMethods.toMutableStateList().apply {
                            remove(paymentMethod)
                        }
                    }
                }
            }
        }
    }
    if (showDialog) {
        AddNewPaymentMethod(paymentMethods, listOfPaymentMethods) { list ->
            showDialog = false
            list.last().copy(number = maskDigitsWithSpaces(list.last().number))
            listOfPaymentMethods = list
        }
    }
}

@Composable
private fun PaymentMethodItem(
    paymentMethod: PaymentMethod,
    onClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var newNumber by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        EditPaymentDialog(
            paymentMethod = paymentMethod,
            name = name,
            onNameChange = { name = it },
            newNumber = newNumber,
            onNumberChange = { newNumber = it },
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
            imageVector = Icons.Outlined.CreditCard,
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            ProductTitle(title = name.ifEmpty { paymentMethod.name })
            ProductDescription(description = newNumber.ifEmpty { paymentMethod.number }, maxLines = 1)
        }
        Spacer(modifier = Modifier.weight(1f))
        ThreeDotMenu(
            onEdit = { showDialog = true },
            onDelete = { onClick() }
        )
    }
}

@Composable
private fun EditPaymentDialog(
    paymentMethod: PaymentMethod,
    name: String,
    onNameChange: (String) -> Unit,
    newNumber: String,
    onNumberChange: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var cardExpire by remember { mutableStateOf(TextFieldValue("")) }
    var cardCVV by remember { mutableStateOf("") }
    PrimaryAlertDialog(
        title = stringResource(R.string.label_delivery_address),
        content = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    placeholder = { Text(text = paymentMethod.name) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newNumber,
                    onValueChange = onNumberChange,
                    placeholder = { Text(text = paymentMethod.number) }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    OutlinedTextField(
                        value = cardExpire,
                        onValueChange = { newText ->
                            cardExpire = formatCardExpiryDate(newText)
                        },
                        placeholder = { Text(text = stringResource(R.string.mm_yy)) },
                        visualTransformation = CardExpiryVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = cardCVV,
                        onValueChange = { newText ->
                            if(newText.length <= 3){
                                cardCVV = newText
                            }
                        },
                        placeholder = { Text(text = stringResource(R.string.cvv)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        onCancel = onDismiss
    ) {
        onDismiss()
    }
}

@Composable
private fun AddNewPaymentMethod(
    paymentMethod: List<PaymentMethod>,
    listOfPaymentMethods: List<PaymentMethod>,
    onClick: (List<PaymentMethod>) -> Unit
) {
    var listOfPaymentMethods1 = listOfPaymentMethods
    var newName by remember { mutableStateOf("") }
    var newPaymentMethod by remember { mutableStateOf("") }
    var cardExpire by remember { mutableStateOf(TextFieldValue("")) }
    var cardCVV by remember { mutableStateOf("") }
    PrimaryAlertDialog(
        title = stringResource(R.string.add_payment_method),
        content = {
            Column {
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    placeholder = { Text(paymentMethod[0].name) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newPaymentMethod,
                    onValueChange = { newPaymentMethod = it },
                    placeholder = { Text(paymentMethod[0].number) }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    OutlinedTextField(
                        value = cardExpire,
                        onValueChange = { newText ->
                            cardExpire = formatCardExpiryDate(newText)
                        },
                        placeholder = { Text(text = stringResource(R.string.mm_yy)) },
                        visualTransformation = CardExpiryVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = cardCVV,
                        onValueChange = { newText ->
                            if(newText.length <= 3){
                                cardCVV = newText
                            }
                        },
                        placeholder = { Text(text = stringResource(R.string.cvv)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        onCancel = { onClick(listOfPaymentMethods1) },
        onConfirm = {
            if (newName.isNotEmpty() && newPaymentMethod.isNotEmpty()) {
                listOfPaymentMethods1 = listOfPaymentMethods1.toMutableList().apply {
                    add(
                        PaymentMethod(
                            id = listOfPaymentMethods1.size.toLong(),
                            name = newName,
                            number = newPaymentMethod,
                            isSelected = false
                        )
                    )
                }
            }
            onClick(listOfPaymentMethods1)
        })
}

private fun formatCardExpiryDate(textFieldValue: TextFieldValue): TextFieldValue {
    val originalText = textFieldValue.text.filter { it.isDigit() }
    val formattedText = buildString {
        if (originalText.length >= 2) {
            append(originalText.substring(0, 2))
            if (originalText.length > 2) {
                append(originalText.substring(2, minOf(4, originalText.length))) // YY
            }
        } else {
            append(originalText)
        }
    }


    val newSelectionPosition = if (originalText.length <= 2) {
        originalText.length
    } else {
        originalText.length + 1
    }

    return textFieldValue.copy(
        text = formattedText,
        selection = TextRange(newSelectionPosition)
    )
}

private fun maskDigitsWithSpaces(input: String): String {
    if (input.length <= 4) return input

    val maskedSection = "*".repeat(input.length - 4)
    val lastFourDigits = input.takeLast(4)
    val maskedCardNumber = maskedSection + lastFourDigits

    return maskedCardNumber.chunked(4).joinToString(" ")
}


@Preview(showBackground = true)
@Composable
private fun BillingDetailsScreenPreview() {
    BillingDetailsScreen(viewModel = BillingDetailsViewModel()) {

    }
}
