package com.mjrfusion.app.calcfusion.model

import androidx.compose.runtime.Composable

data class PagerItem (
    val content: @Composable () -> Unit
)