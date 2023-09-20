package com.example.kalkulator.calculatorAlgorithm

interface Operator {
    fun calculate(previous: Float, actual: Float): Float
}

class Plus : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous + actual
    }
}

class Minus : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous - actual
    }
}

class Times : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous * actual
    }
}

class Divide : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous / actual
    }
}

class Percent : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return (previous/actual) * 100
    }
}