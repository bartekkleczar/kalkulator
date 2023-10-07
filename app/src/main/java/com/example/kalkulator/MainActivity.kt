package com.example.kalkulator

import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalkulator.calculatorAlgorithm.Divide
import com.example.kalkulator.calculatorAlgorithm.Minus
import com.example.kalkulator.calculatorAlgorithm.Operator
import com.example.kalkulator.calculatorAlgorithm.Percent
import com.example.kalkulator.calculatorAlgorithm.Plus
import com.example.kalkulator.calculatorAlgorithm.Times
import com.example.kalkulator.calculatorAlgorithm.calculate
import com.example.kalkulator.calculatorAlgorithm.displayToString
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
    val calculations = remember {
        mutableStateListOf<Any>()
    }
    TopBar()
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxSize(0.5f)
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, end = 20.dp),
            text = calculations.joinToString(separator = "") { displayToString(it) },
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
        )
        val plus = Plus()
        val minus = Minus()
        val times = Times()
        val divide = Divide()
        val percent = Percent()
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            CalculatorButton(text = "AC") {
                calculations.clear()
            }
            CalculatorButton(text = "C") {
                if(calculations.isNotEmpty()){
                    calculations.remove(calculations.last())
                }
            }
            CalculatorButton(text = "%") {
                when(calculations.last()) {
                    !is Operator -> calculations.add(percent)
                    else -> calculations[calculations.size-1] = percent
                }
            }
            CalculatorButton(text = "/") {
                when(calculations.last()) {
                    !is Operator -> calculations.add(divide)
                    else -> calculations[calculations.size-1] = divide
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            CalculatorButton(text = "7") {
                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add("7")
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}7"
                }
            }
            CalculatorButton(text = "8") {
                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add("8")
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}8"
                }
            }
            CalculatorButton(text = "9") {
                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add(9)
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}9"
                }
            }
            CalculatorButton(text = "X") {
                when(calculations.last()) {
                    !is Operator -> calculations.add(times)
                    else -> calculations[calculations.size-1] = times
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            CalculatorButton(text = "4") {

                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add("4")
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}4"
                } 
            }
            CalculatorButton(text = "5") {
                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add("5")
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}5"
                }
            }
            CalculatorButton(text = "6") {
                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add("6")
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}6"
                }
            }
            CalculatorButton(text = "-") {
                when(calculations.last()) {
                    !is Operator -> calculations.add(minus)
                    else -> calculations[calculations.size-1] = minus
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            CalculatorButton(text = "1") {
                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add("1")
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}1"
                }
            }
            CalculatorButton(text = "2") {
                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add("2")
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}2"
                }
            }
            CalculatorButton(text = "3") {
                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add("3")
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}3"
                }
            }
            CalculatorButton(text = "+") {
                when(calculations.last()) {
                    !is Operator -> calculations.add(plus)
                    else -> calculations[calculations.size-1] = plus
                }
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
                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add("0")
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}0"
                }
            }
            CalculatorButton(text = ".") {
                if (calculations.isEmpty() || calculations.last() !is String) {
                    calculations.add("0.")
                } else {
                    calculations[calculations.size-1] = "${calculations.last()}."
                }
            }
            CalculatorButton(text = "=") {
                Log.e("Main", "${calculations.toList()}")
                Log.e("Main",calculate(calculations).toString())
                calculations.add("=${calculate(calculations)}")
            }
        }
        Spacer(modifier = Modifier.height(spacer))
    }
}