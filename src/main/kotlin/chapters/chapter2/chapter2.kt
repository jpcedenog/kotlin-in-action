package chapters.chapter2

import java.util.TreeMap
import java.io.BufferedReader
import java.io.StringReader
import utils.Color
import utils.Color.*

fun main(args: Array<String>){
    utils.printTitle(2)

    println(max(1, 3))

    println("The name is ${if(args.size > 0) args[0] else "otherName"}!")

    val rectangle = Rectangle(2, 3)
    println("Is a square? ${rectangle.isSquare}")

    println(getMnemonic(BLUE))
    println(getWarmth(ORANGE))
    println(mix(BLUE, YELLOW))
    println(mixOptimized(BLUE, YELLOW))

    try {
        println(mix(Color.RED, Color.VIOLET)) //Throws an exception! Catch it!
    }catch(e: Exception){
        println("Exception caught! ${e.message}")
    }

    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))

    println()
    val oneToForty = 1..40
    for(i in oneToForty) {
        print(fizzBuzz(i))
    }
    println()
    for(i in 1..40) {
        print(fizzBuzz(i))
    }

    println("\nCount backward")
    for(i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }

    println("\nCount forward")
    for(i in 1 until 100 + 1 step 2) {
        print(fizzBuzz(i))
    }
    
    println()
    iterateOverMap()

    println(isLetter('Q'))
    println(isNotDigit('4'))
    println(recognize('T'))
    println(recognize('5'))
    println(recognize('#'))
    readNumberExpr(BufferedReader(StringReader("7")))
    readNumberExpr(BufferedReader(StringReader("foo")))

    println("Kotlin" in setOf("Java", "Scala"))
    println("Kotlin" in "Java".."Scala") //The same as "Java" <= "Kotlin" && "Kotlin" <= "Scala" 
}

fun max(a: Int, b: Int) = if(a > b) a else b

class Rectangle (val height: Int, val width: Int) {
    /* Custom accessor */
    val isSquare: Boolean get() = height == width
}

fun getMnemonic(color: Color) = when (color) {
    RED -> "Richard"
    ORANGE -> "Of"
    YELLOW -> "York"
    GREEN -> "Gave"
    BLUE -> "Battle"
    INDIGO -> "In"
    VIOLET -> "Vain"
}

fun getWarmth(color: Color) = when(color) {
    RED, ORANGE, YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE, INDIGO, VIOLET -> "cold"
}

fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("Dirty color")
}

fun mixOptimized(c1: Color, c2: Color) = when {
    (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
    (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
    (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
    else -> throw Exception("Dirty color")
}

interface Expr
class Num(val value: Int): Expr
class Sum(val left: Expr, val right: Expr): Expr

fun eval(e: Expr): Int = when(e) {
    is Num -> e.value
    is Sum -> eval(e.right) + eval(e.left)
    else -> throw IllegalArgumentException("Unknown expression")
}

fun fizzBuzz (i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun iterateOverMap() {
    val binaryReps = TreeMap<Char, String>()

    for(c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }

    for((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'
fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "It's a digit"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter"
    else -> "Unrecognizable"
}

fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    }
    catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}

/* The function below uses "try" as an expression */
fun readNumberExpr(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return
    }

    println("The number is: $number")
}
