package com.example.kalkulator.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(text: String, state: Boolean, modifier: Modifier = Modifier, onclick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val sH = configuration.screenHeightDp.dp
    val sW = configuration.screenWidthDp.dp
    FilledTonalButton(modifier = modifier
        .padding(start = paddingState(state), end = paddingState(state))
        .width(sW/5)
        .height(sH/11),
        onClick = { onclick() }
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
fun paddingState(state: Boolean): Dp{
    return when(state){
        true -> 0.dp
        false -> 10.dp
    }
}