package com.example.kalkulator.calculatorAlgorithm

fun displayToString(item: Any): String {
    return when(item){
        is Operator -> item.display()
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
        return "+"
    }

}

class Minus : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous - actual
    }

    override fun display(): String {
        return "-"
    }
}

class Times : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous * actual
    }

    override fun display(): String {
        return "*"
    }
}

class Divide : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return previous / actual
    }

    override fun display(): String {
        return "/"
    }
}

class Percent : Operator {
    override fun calculate(previous: Float, actual: Float): Float {
        return (previous/100) * actual
    }

    override fun display(): String {
        return "%"
    }
}