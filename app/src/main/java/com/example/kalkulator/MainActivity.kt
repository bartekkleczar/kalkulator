package com.example.kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kalkulator.composables.CalculatorButton
import com.example.kalkulator.composables.TopBar
import com.example.kalkulator.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetLayout()
                }
            }
        }
    }
}

@Composable
fun SetLayout(){
    val configuration = LocalConfiguration.current
    val sH = configuration.screenHeightDp.dp
    val sW = configuration.screenWidthDp.dp
    val spacer = sH/150
    TopBar()
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxSize(0.5f)
    ){
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            CalculatorButton(text = "AC") {

            }
            CalculatorButton(text = "C") {

            }
            CalculatorButton(text = "%") {

            }
            CalculatorButton(text = "/") {

            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            CalculatorButton(text = "7") {

            }
            CalculatorButton(text = "8") {

            }
            CalculatorButton(text = "9") {

            }
            CalculatorButton(text = "X") {

            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            CalculatorButton(text = "4") {

            }
            CalculatorButton(text = "5") {

            }
            CalculatorButton(text = "6") {

            }
            CalculatorButton(text = "-") {

            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            CalculatorButton(text = "1") {

            }
            CalculatorButton(text = "2") {

            }
            CalculatorButton(text = "3") {

            }
            CalculatorButton(text = "+") {

            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            CalculatorButton(text = " ") {

            }
            CalculatorButton(text = "0") {

            }
            CalculatorButton(text = ".") {

            }
            CalculatorButton(text = "=") {

            }
        }
        Spacer(modifier = Modifier.height(spacer))
    }
}