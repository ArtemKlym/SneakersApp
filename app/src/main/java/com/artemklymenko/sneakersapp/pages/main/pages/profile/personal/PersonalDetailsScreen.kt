package com.artemklymenko.sneakersapp.pages.main.pages.profile.personal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.core.base.BaseContentLayout
import com.artemklymenko.sneakersapp.core.components.TopBarAsText
import com.artemklymenko.sneakersapp.domain.models.User

@Composable
fun PersonalDetailsScreen(
    viewModel: PersonalDetailsViewModel,
    onBackClick: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            PersonalDetailsUiEvent.LoadPersonalDetailsData
        )
    }
    BaseContentLayout(viewModel = viewModel) { uiState ->
        uiState?.let {
            PersonalDetailsScreenContent(it.person)
        }
    }
    BackHandler {
        onBackClick()
    }
}

@Composable
private fun PersonalDetailsScreenContent(person: User) {
    Scaffold(
        topBar = {
            TopBarAsText(title = stringResource(id = R.string.personal_data_details))
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.my_information),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
            PersonField(
                title = stringResource(R.string.name),
                description = person.name,
            )
            PersonField(
                title = stringResource(R.string.surname),
                description = person.surname,
            )
            PersonField(
                title = stringResource(R.string.email),
                description = person.email,
            )
        }
    }

}

@Composable
fun PersonField(title: String, description: String) {
    var textField by remember { mutableStateOf(description) }
    Column(
       modifier = Modifier
           .fillMaxWidth()
           .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        TextField(
            value = textField,
            onValueChange = {
                textField = it
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PersonalDetailsScreenPreview(){
    PersonalDetailsScreen(viewModel = PersonalDetailsViewModel()) {

    }
}
