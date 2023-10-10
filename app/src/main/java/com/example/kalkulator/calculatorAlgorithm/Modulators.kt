package com.example.kalkulator.calculatorAlgorithm

import kotlin.math.ln

interface Modulator{
    fun calculate(victim: Float): Float
    fun display(): String
}

class Factorial(): Modulator{
    override fun calculate(victim: Float): Float {
        return if (victim == 0f) {
            1f
        } else {
            victim * calculate(victim - 1f)
        }
    }

    override fun display(): String {
        return "!"
    }
}

class Ln(): Modulator{
    override fun calculate(victim: Float): Float {
        return ln(victim)
    }

    override fun display(): String {
        return "ln()"
    }
}