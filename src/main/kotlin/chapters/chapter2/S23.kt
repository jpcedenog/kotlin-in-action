package chapters.chapter2

import utils.Color
import utils.Color.*

fun main(args: Array<String>) {
    println(getMnemonic(BLUE))
    println(getWarmth(ORANGE))
    println(mix(BLUE, YELLOW))
    try {
        println(mix(Color.RED, Color.VIOLET)) //Throws an exception! Catch it!
    } catch (e: Exception) {
        println("Exception caught! ${e.message}")
    }
    println(mixOptimized(BLUE, YELLOW))
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}

/* Return a 'when' expression directly */
fun getMnemonic(color: Color) = when (color) {
    RED -> "Richard"
    ORANGE -> "Of"
    YELLOW -> "York"
    GREEN -> "Gave"
    BLUE -> "Battle"
    INDIGO -> "In"
    VIOLET -> "Vain"
}

/* Combine options in one when branch */
fun getWarmth(color: Color) = when (color) {
    RED, ORANGE, YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE, INDIGO, VIOLET -> "cold"
}

/* Use different objects in when branches */
fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("Dirty color")
}

/* Optimized version that does not create unnecessary objects */
fun mixOptimized(c1: Color, c2: Color) = when {
    (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
    (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
    (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
    else -> throw Exception("Dirty color")
}

interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

/* Smart casts. e is converted to either Num or Sum automatically */
fun eval(e: Expr): Int = when (e) {
    is Num -> {
        println("num: ${e.value}")
        e.value //last expression is returned
    }
    is Sum -> {
        val left = eval(e.left)
        val right = eval(e.right)
        println("sum: $left + $right")
        left + right
    }
    else -> throw IllegalArgumentException("Unknown expression")
}
