package com.example.kalkulator.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(text: String, modifier: Modifier = Modifier, onclick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val sH = configuration.screenHeightDp.dp
    val sW = configuration.screenWidthDp.dp
    FilledTonalButton(modifier = modifier.width(sW/5).height(sH/10), onClick = { onclick }) {
        Text(
            text = text,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}