package com.mjrfusion.app.calcfusion.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.DarkGray,
    textStyle: TextStyle = TextStyle.Default,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(48.dp))
            .background(color)
            .aspectRatio(1F)
            .weight(1F)
            .clickable(onClick = onClick)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun ColumnScope.CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.DarkGray,
    textStyle: TextStyle = TextStyle.Default,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(48.dp))
            .background(color)
            .aspectRatio(1F)
            .weight(1F), contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}