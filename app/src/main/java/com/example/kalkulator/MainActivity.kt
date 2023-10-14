package com.example.kalkulator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalkulator.calculatorAlgorithm.Divide
import com.example.kalkulator.calculatorAlgorithm.Factorial
import com.example.kalkulator.calculatorAlgorithm.Lg
import com.example.kalkulator.calculatorAlgorithm.Ln
import com.example.kalkulator.calculatorAlgorithm.Minus
import com.example.kalkulator.calculatorAlgorithm.Modulator
import com.example.kalkulator.calculatorAlgorithm.Operator
import com.example.kalkulator.calculatorAlgorithm.Percent
import com.example.kalkulator.calculatorAlgorithm.Plus
import com.example.kalkulator.calculatorAlgorithm.Power
import com.example.kalkulator.calculatorAlgorithm.Root
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
fun SetLayout() {
    val configuration = LocalConfiguration.current
    val sH = configuration.screenHeightDp.dp
    val sW = configuration.screenWidthDp.dp
    val spacer = sH / 150
    val calculations = remember {
        mutableStateListOf<Any>()
    }
    TopBar()
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxSize(0.5f)
    ) {
        var wasSummed by remember {
            mutableStateOf(false)
        }
        val yOffset by animateDpAsState(
            targetValue = if (wasSummed) 20.dp else (-10).dp,
            animationSpec = tween(500), label = ""
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, end = 20.dp)
                .offset(y = yOffset),
            text = calculations.joinToString(separator = "") { displayToString(it) },
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            lineHeight = 50.sp,
            softWrap = true
        )
        var sum by remember {
            mutableStateOf("")
        }
        val enterExitAnimationDurationInt: FiniteAnimationSpec<IntOffset> =
            tween(durationMillis = 500)
        val enterExitAnimationDurationFloat: FiniteAnimationSpec<Float> =
            tween(durationMillis = 500)
        AnimatedVisibility(
            visible = wasSummed,
            enter = slideInVertically(
                animationSpec = enterExitAnimationDurationInt
            ) + fadeIn(
                animationSpec = enterExitAnimationDurationFloat
            ),
            exit = slideOutVertically(
                animationSpec = enterExitAnimationDurationInt
            ) + fadeOut(
                animationSpec = enterExitAnimationDurationFloat
            )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, end = 20.dp),
                text = sum,
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                lineHeight = 50.sp,
                softWrap = true
            )
        }
        val plus = Plus()
        val minus = Minus()
        val times = Times()
        val divide = Divide()
        val percent = Percent()
        val power = Power()
        val root = Root()
        val lg = Lg()
        val ln = Ln()
        val factorial = Factorial()

        val state = remember {
            MutableTransitionState(false).apply {
                targetState = false
            }
        }
        AnimatedVisibility(
            visibleState = state,
        ) {
            Row(
                Modifier.fillMaxWidth()
            ) {
                CalculatorButton(text = "^", state.targetState) {
                    if (wasSummed) {
                        wasSummed = !wasSummed
                    }
                    when (calculations.last()) {
                        !is Operator -> calculations.add(power)
                        else -> calculations[calculations.size - 1] = power
                    }
                }
                CalculatorButton(text = "lg", state.targetState) {
                    if (wasSummed) {
                        wasSummed = !wasSummed
                    }
                    when (calculations.last()) {
                        !is Operator -> calculations.add(lg)
                        else -> calculations[calculations.size - 1] = lg
                    }
                }
                CalculatorButton(text = "ln", state.targetState) {
                    if (wasSummed) {
                        wasSummed = !wasSummed
                    }
                    when (calculations.last()) {
                        !is Modulator -> calculations.add(ln)
                        else -> calculations[calculations.size - 1] = ln
                    }
                }
                CalculatorButton(text = "(", state.targetState) {
                    if (wasSummed) {
                        wasSummed = !wasSummed
                    }
                    calculations.add("(")
                }
                CalculatorButton(text = ")", state.targetState) {
                    if (wasSummed) {
                        wasSummed = !wasSummed
                    }
                    calculations.add(")")
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
        ) {
            AnimatedVisibility(
                visibleState = state,
            ) {
                CalculatorButton(text = "√", state.targetState) {
                    if (wasSummed) {
                        wasSummed = !wasSummed
                    }
                    if (calculations.isEmpty()) calculations.add(root)
                    when (calculations.last()) {
                        !is Operator -> calculations.add(root)
                        else -> calculations[calculations.size - 1] = root
                    }
                }
            }
            CalculatorButton(text = "AC", state.targetState) {
                calculations.clear()
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
            }
            CalculatorButton(text = "C", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                } else {
                    if (calculations.isNotEmpty()) {
                        calculations.remove(calculations.last())
                    }
                }

            }
            CalculatorButton(text = "%", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                when (calculations.last()) {
                    !is Operator -> calculations.add(percent)
                    else -> calculations[calculations.size - 1] = percent
                }
            }
            CalculatorButton(text = "÷", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                when (calculations.last()) {
                    !is Operator -> calculations.add(divide)
                    else -> calculations[calculations.size - 1] = divide
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
        ) {
            AnimatedVisibility(
                visibleState = state,
            ) {

                CalculatorButton(text = "!", state.targetState) {
                    if (wasSummed) {
                        wasSummed = !wasSummed
                    }
                    when (calculations.last()) {
                        !is Modulator -> calculations.add(factorial)
                        else -> calculations[calculations.size - 1] = percent
                    }
                }
            }
            CalculatorButton(text = "7", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("7")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}7"
                }
            }
            CalculatorButton(text = "8", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("8")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}8"
                }
            }
            CalculatorButton(text = "9", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("9")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}9"
                }
            }
            CalculatorButton(text = "×", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                when (calculations.last()) {
                    !is Operator -> calculations.add(times)
                    else -> calculations[calculations.size - 1] = times
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
        ) {
            AnimatedVisibility(
                visibleState = state,
            ) {
                CalculatorButton(text = "e", state.targetState) {
                    if (wasSummed) {
                        wasSummed = !wasSummed
                    }

                }
            }
            CalculatorButton(text = "4", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }

                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("4")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}4"
                }
            }
            CalculatorButton(text = "5", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("5")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}5"
                }
            }
            CalculatorButton(text = "6", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("6")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}6"
                }
            }
            CalculatorButton(text = "-", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                when (calculations.last()) {
                    !is Operator -> calculations.add(minus)
                    else -> calculations[calculations.size - 1] = minus
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
        ) {
            AnimatedVisibility(
                visibleState = state,
            ) {
                CalculatorButton(text = "π", state.targetState) {
                    if (wasSummed) {
                        wasSummed = !wasSummed
                    }

                }
            }
            CalculatorButton(text = "1", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("1")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}1"
                }
            }
            CalculatorButton(text = "2", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("2")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}2"
                }
            }
            CalculatorButton(text = "3", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("3")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}3"
                }
            }
            CalculatorButton(text = "+", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                when (calculations.last()) {
                    !is Operator -> calculations.add(plus)
                    else -> calculations[calculations.size - 1] = plus
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CalculatorButton(text = " ", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                state.targetState = !state.targetState
            }
            CalculatorButton(text = "0", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("0")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}0"
                }
            }
            CalculatorButton(text = ".", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                if (calculations.isEmpty() || calculations.last() !is String || calculations.last() == "(" || calculations.last() == ")") {
                    calculations.add("0.")
                } else {
                    calculations[calculations.size - 1] = "${calculations.last()}."
                }
            }
            CalculatorButton(text = "=", state.targetState) {
                if (wasSummed) {
                    wasSummed = !wasSummed
                }
                Log.e("Main", "${calculations.toList()}")
                Log.e("Main", calculate(calculations).toString())
                sum = (" = ${calculate(calculations)}")
                wasSummed = true
            }
        }
        Spacer(modifier = Modifier.height(spacer))
    }
}