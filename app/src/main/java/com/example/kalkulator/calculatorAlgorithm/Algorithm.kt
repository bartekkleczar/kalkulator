package com.example.kalkulator.calculatorAlgorithm

import android.util.Log
import java.math.RoundingMode
import java.text.DecimalFormat
import com.example.kalkulator.calculatorAlgorithm.BracketState.*

fun calculate(input: List<Any>): Any {
    val input = input.toMutableList()
    var inputCopy = input.toMutableList()

    if("(" in input){
        val bracketsStart = mutableListOf<Int>()
        val bracketsEnd = mutableListOf<Int>()
        for (i in inputCopy) {
            if (i == "(") {
                bracketsStart.add(inputCopy.indexOf(i))
                input.remove(i)
            }
            if (i == ")") {
                bracketsEnd.add(inputCopy.indexOf(i))
                input.remove(i)
            }
        }

        val toCalculate = mutableListOf<Any>()

        var state = didntChanged
        val injectionPlaces = mutableSetOf<Int>()
        while (state == didntChanged) {
            var index = 0
            for (i in inputCopy) {
                if (i == ")") {
                    state = changedFalse
                }
                if (state == changedTrue) {
                    toCalculate.add(i)
                    input.remove(i)
                }
                if (i == "(") {
                    state = changedTrue
                    injectionPlaces.add(index)
                }
                if (state == changedFalse || state == didntChanged) index++
            }
            val toCalculateCopy = toCalculate.toMutableList()
            for (i in toCalculateCopy) {
                if (i is Modulator) {
                    val location = toCalculate.indexOf(i)
                    toCalculate[location] =
                        i.calculate(toCalculate[location - 1].toString().toFloat())
                    toCalculate.removeAt(location - 1)
                }
            }

            for (i in toCalculateCopy) {
                if (i is Operator && (i is Times || i is Divide || i is Percent || i is Power || i is Root)) {
                    val location = toCalculate.indexOf(i)
                    val base =
                        if (i !is Root) toCalculate[location - 1].toString().toFloat() else 2f
                    toCalculate[location] =
                        i.calculate(base, toCalculate[location + 1].toString().toFloat())
                    toCalculate.removeAt(location + 1)
                    if (i !is Root) toCalculate.removeAt(location - 1)
                }
            }
            for (i in toCalculateCopy) {
                if (i is Operator && (i is Plus || i is Minus)) {
                    val location = toCalculate.indexOf(i)
                    toCalculate[location] = i.calculate(
                        toCalculate[location - 1].toString().toFloat(),
                        toCalculate[location + 1].toString().toFloat()
                    )
                    toCalculate.removeAt(location + 1)
                    toCalculate.removeAt(location - 1)
                }
            }
            for ((j, i) in injectionPlaces.withIndex()) {
                input.add(i, toCalculate[j].toString().toFloat())
            }
        }
    }

    val inputCopyAfterBrackets = input.toMutableList()
    Log.e("Main", inputCopyAfterBrackets.toString())
    for (i in inputCopyAfterBrackets) {
        if (i is Modulator) {
            val location = input.indexOf(i)
            input[location] = i.calculate(input[location - 1].toString().toFloat())
            input.removeAt(location - 1)
        }
    }
    Log.e("Main", inputCopyAfterBrackets.toString())
    for (i in inputCopyAfterBrackets) {
        if (i is Operator && (i is Times || i is Divide || i is Percent || i is Power || i is Root)) {
            val location = input.indexOf(i)
            val base = if (i !is Root) input[location - 1].toString().toFloat() else 2f
            input[location] = i.calculate(base, input[location + 1].toString().toFloat())
            input.removeAt(location + 1)
            if (i !is Root) input.removeAt(location - 1)
        }
    }
    Log.e("Main", inputCopyAfterBrackets.toString())
    var out = 0f
    for (i in inputCopyAfterBrackets) {
        if (i is Operator && (i is Plus || i is Minus)) {
            val location = input.indexOf(i)
            input[location] = i.calculate(
                input[location - 1].toString().toFloat(),
                input[location + 1].toString().toFloat()
            )
            input.removeAt(location + 1)
            input.removeAt(location - 1)
        }

    }
    if(input.size >= 2){
        val inputCopyOptionalTimesInjection = input.toMutableList()
        Log.e("Main", inputCopyOptionalTimesInjection.toString())
        for((iteration, i) in inputCopyOptionalTimesInjection.withIndex()){
            if(iteration % 2 != 0){
                input.add(iteration, Times())
            }
        }
        val inputCopyOptionalInjectedTimesCalculation = input.toMutableList()
        for (i in inputCopyOptionalInjectedTimesCalculation) {
            if (i is Times ) {
                val location = input.indexOf(i)
                input[location] = i.calculate(input[location - 1].toString().toFloat(), input[location + 1].toString().toFloat())
                input.removeAt(location + 1)
                input.removeAt(location - 1)
            }
        }
    }
    out = input[0].toString().toFloat()
    return if ((out % 1f) == 0f) {
        out.toInt()
    } else {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.HALF_UP
        df.format(out)
    }
}

