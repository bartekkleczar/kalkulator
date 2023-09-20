package com.example.kalkulator.calculatorAlgorithm

fun calculate(input: List<Any>): Any {
    val input = input.toMutableList()
    val inputCopy = input.toMutableList()
    for (i in inputCopy) {
        if (i is Operator && (i is Times || i is Divide || i is Percent)) {
            val location = input.indexOf(i)
            input[location] = i.calculate(input[location - 1].toString().toFloat(), input[location + 1].toString().toFloat())
            input.removeAt(location + 1)
            input.removeAt(location -1)
        }
    }

    var out = 0f
    for (i in inputCopy) {
        if (i is Operator && (i is Plus || i is Minus)) {
            val location = input.indexOf(i)
            input[location] = i.calculate(input[location - 1].toString().toFloat(), input[location + 1].toString().toFloat())
            input.removeAt(location + 1)
            input.removeAt(location -1)
        }
        out = input[0].toString().toFloat()
    }
    return if((out % 1f) == 0f){
        out.toInt()
    }else{
        out
    }
}

