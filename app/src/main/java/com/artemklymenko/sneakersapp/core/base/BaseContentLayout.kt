package com.artemklymenko.sneakersapp.core.base

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.artemklymenko.sneakersapp.core.base.common.events.CallBack
import com.artemklymenko.sneakersapp.core.base.common.events.Dialog
import com.artemklymenko.sneakersapp.core.base.common.events.Progress
import com.artemklymenko.sneakersapp.core.base.common.state.UiState

@Composable
fun <S : UiState, P : Progress, D : Dialog, C : CallBack> BaseContentLayout(
    viewModel: BaseViewModel<S, P, D, C>,
    onBackPressed: (() -> Unit)? = null,
    content: @Composable (uiState: S?) -> Unit
) {
    BackHandler(
        enabled = onBackPressed != null,
        onBack = {
            onBackPressed?.invoke()
        }
    )

    val uiState = viewModel.uiState
    val eventState = viewModel.eventState

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ){
        content(uiState.value)
    }
}