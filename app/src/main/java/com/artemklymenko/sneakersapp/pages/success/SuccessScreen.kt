package com.artemklymenko.sneakersapp.pages.success

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.components.PrimaryButton

@Composable
fun SuccessScreen(
    viewModel: SuccessViewModel,
    onConfirmationClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        content = {
            SuccessScreenContent(
                modifier = Modifier.padding(it),
                onConfirmationClick = onConfirmationClick
            )
        }
    )
    BackHandler {
        onBackClick.invoke()
    }
}

@Composable
private fun SuccessScreenContent(
    modifier: Modifier,
    onConfirmationClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier.weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null,
                modifier = modifier
                    .size(128.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = stringResource(R.string.order_success),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = stringResource(R.string.order_confirmation),
                fontWeight = FontWeight.Light,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)
            )
        }
        PrimaryButton(
            text = stringResource(id = R.string.back_to_home),
            modifier = modifier
                .weight(0.2f)
                .padding(horizontal = 16.dp),
        ) {
            onConfirmationClick.invoke()
        }
    }

}

@Preview
@Composable
private fun SuccessScreenPreview(){
    SuccessScreen(viewModel = SuccessViewModel(), onConfirmationClick = { /*TODO*/ }) {

    }
}
