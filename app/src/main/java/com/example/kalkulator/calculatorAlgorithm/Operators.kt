package com.example.kalkulator.calculatorAlgorithm

import kotlin.math.ln
import kotlin.math.log
import kotlin.math.pow

fun displayToString(item: Any): String {
    return when(item){
        is Operator -> item.display()
        is Modulator -> item.display()
        else -> item.toString()
    }
}
interface Operator {
    fun calculate(previous: Float, actual: Float): Float
    fun display(): String
}

class Plus : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous + actual
    }

    override fun display(): String {
        return " + "
    }

}

class Minus : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous - actual
    }

    override fun display(): String {
        return " - "
    }
}

class Times : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous * actual
    }

    override fun display(): String {
        return " × "
    }
}

class Divide : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous / actual
    }

    override fun display(): String {
        return " ÷ "
    }
}

class Percent : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return (previous/100) * actual
    }

    override fun display(): String {
        return " % "
    }
}

class Lg : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return log(actual, previous) // base/number
    }

    override fun display(): String {
        return " log "
    }
}

class Root : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return actual.pow(1f/previous)
    }

    override fun display(): String {
        return " ^√ "
    }
}

class Power : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous.pow(actual)
    }

    override fun display(): String {
        return " ^ "
    }
}