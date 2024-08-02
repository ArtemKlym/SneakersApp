package com.artemklymenko.sneakersapp.pages.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.utils.appToastShow
import com.artemklymenko.sneakersapp.widgets.BackIconButton
import com.artemklymenko.sneakersapp.widgets.ButtonPrimary
import com.artemklymenko.sneakersapp.widgets.ClickableTextSecondary
import java.util.Locale

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    onBackClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    onLoginGoogleClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        SignInScreenContent(
            viewModel = viewModel,
            onBackClick = onBackClick,
            onForgotPasswordClick = onForgotPasswordClick,
            onLoginClick = onLoginClick,
            onLoginGoogleClick = onLoginGoogleClick,
            onRegisterClick = onRegisterClick,
            modifier = Modifier.padding(it)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    SignInScreen(
        viewModel = SignInViewModel(),
        onBackClick = { /*TODO*/ },
        onForgotPasswordClick = { /*TODO*/ },
        onLoginClick = { /*TODO*/ },
        onLoginGoogleClick = { /*TODO*/ }) {

    }
}

@Composable
private fun SignInScreenContent(
    modifier: Modifier,
    viewModel: SignInViewModel,
    onBackClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    onLoginGoogleClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        val context = LocalContext.current
        var login by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Box {
            BackIconButton(onClick = onBackClick)
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(224.dp),
                painter = painterResource(id = R.drawable.sneakers_image),
                contentDescription = null
            )
        }

        Text(
            modifier = modifier.padding(top = 16.dp),
            text = stringResource(R.string.login),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = login,
            onValueChange = { login = it.trim() },
            label = {
                Text(
                    text = stringResource(R.string.email),
                    fontWeight = FontWeight.Bold
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = stringResource(R.string.email)
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it.trim() },
            label = {
                Text(
                    text = stringResource(R.string.password),
                    fontWeight = FontWeight.Bold
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = stringResource(R.string.password)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            ClickableTextSecondary(
                text = stringResource(id = R.string.forgot_password),
                onClick = onForgotPasswordClick
            )
        }

        ButtonPrimary(text = stringResource(id = R.string.login)) {
            if (viewModel.loginRequest(login, password)) {
                onLoginClick()
            } else {
                appToastShow(context.getString(R.string.login_or_password), context)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                color = Color(0xFFB8B8B8), thickness = 2.dp,
                modifier = Modifier.weight(1f)
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stringResource(id = R.string.or).uppercase(Locale.ROOT),
                fontSize = 20.sp,
                style = TextStyle(color = Color(0xFFB8B8B8))
            )
            HorizontalDivider(
                color = Color(0xFFB8B8B8), thickness = 2.dp,
                modifier = Modifier.weight(1f)
            )
        }
        ButtonPrimary(
            text = stringResource(id = R.string.login_with_google),
            onClick = onLoginGoogleClick
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val register = stringResource(id = R.string.register)
            val annotatedString = buildAnnotatedString {
                append("${stringResource(R.string.new_to)} ${stringResource(R.string.app_name)}? ")
                withStyle(
                    style = SpanStyle(color = Color(0xFF0EA9F8))
                ){
                    pushStringAnnotation(tag = register, annotation = register)
                    append(register)
                }
            }
            ClickableText(
                text = annotatedString,
                style = TextStyle(fontSize = 16.sp)
            ) { offset ->
                annotatedString.getStringAnnotations(offset,offset)
                    .firstOrNull()
                    ?.let { span ->
                        when {
                            register === span.item -> onRegisterClick()
                        }
                    }
            }
        }
    }
}