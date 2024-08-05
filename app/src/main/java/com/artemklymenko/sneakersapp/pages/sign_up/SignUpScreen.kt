package com.artemklymenko.sneakersapp.pages.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.widgets.BackIconButton
import com.artemklymenko.sneakersapp.widgets.ButtonPrimary
import java.util.Locale

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {
    Scaffold(
        content = {
            SignUpScreenContent(
                modifier = Modifier.padding(it),
                viewModel = viewModel,
                onBackClick = onBackClick,
                onNextClick = onNextClick,
                onTermsClick = onTermsClick,
                onPrivacyClick = onPrivacyClick
            )
        }
    )
}

@Composable
private fun SignUpScreenContent(
    modifier: Modifier,
    viewModel: SignUpViewModel,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
) {
    var login by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Box {
            BackIconButton(onClick = onBackClick)
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(224.dp),
                painter = painterResource(id = R.drawable.sneakers_image), contentDescription = null
            )
        }
        Text(
            text = stringResource(id = R.string.sign_up),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = login,
            onValueChange = {
                login = it.trim()
            },
            label = {
                Text(
                    text = stringResource(id = R.string.email),
                    fontWeight = FontWeight.Bold
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = stringResource(id = R.string.email)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF6F6F6),
                unfocusedContainerColor = Color(0xFFF6F6F6),
                disabledContainerColor = Color(0xFFF6F6F6),
                focusedTextColor = Color(0xFF9D9D9D),
                focusedLabelColor = Color(0xFF222222)
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it.trim() },
            label = {
                Text(
                    text = stringResource(id = R.string.person),
                    fontWeight = FontWeight.Bold
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(id = R.string.person)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF6F6F6),
                unfocusedContainerColor = Color(0xFFF6F6F6),
                disabledContainerColor = Color(0xFFF6F6F6),
                focusedTextColor = Color(0xFF9D9D9D),
                focusedLabelColor = Color(0xFF222222)
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it.trim() },
            label = {
                Text(
                    text = stringResource(id = R.string.phone_number),
                    fontWeight = FontWeight.Bold
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = stringResource(id = R.string.phone_number)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF6F6F6),
                unfocusedContainerColor = Color(0xFFF6F6F6),
                disabledContainerColor = Color(0xFFF6F6F6),
                focusedTextColor = Color(0xFF9D9D9D),
                focusedLabelColor = Color(0xFF222222)
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        PrivacyBlock(onTermsClick, onPrivacyClick)
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            ButtonPrimary(
                text = stringResource(id = R.string.continue_text),
                onClick = onNextClick
            )
        }
    }
}

@Composable
private fun PrivacyBlock(onTermsClick: () -> Unit, onPrivacyClick: () -> Unit) {
    val tnc = stringResource(id = R.string.terms_conditions)
    val privacyPolicy = stringResource(id = R.string.privacy_policy)
    val annotatedString = buildAnnotatedString {
        append("${stringResource(id = R.string.register_agree_text)} ")
        withStyle(
            style = SpanStyle(color = Color(0xFF0EA9F8))
        ) {
            pushStringAnnotation(tag = tnc, annotation = tnc)
            append(tnc)
        }
        append(" ${stringResource(id = R.string.and_text).lowercase(Locale.ROOT)} ")
        withStyle(
            style = SpanStyle(color = Color(0xFF0EA9F8))
        ) {
            pushStringAnnotation(tag = privacyPolicy, annotation = privacyPolicy)
            append(privacyPolicy)
        }
    }
    ClickableText(
        modifier = Modifier.padding(top = 8.dp),
        text = annotatedString,
        style = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center),
    ) { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()
            ?.let { span ->
                when {
                    tnc === span.item -> onTermsClick()
                    else -> onPrivacyClick()
                }
            }
    }
}
